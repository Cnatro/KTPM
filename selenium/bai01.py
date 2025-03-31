from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import time

service = Service(executable_path='.venv/chromedriver.exe')
driver = webdriver.Chrome(service=service)
driver.get('https://dhthanhit.pythonanywhere.com/')

print(driver.title)
kw =  input("Your keywords :")

e = driver.find_element(By.NAME,'keyword')
e.send_keys(kw)

driver.find_element(By.CSS_SELECTOR,'nav button[type=submit]').click()
time.sleep(2)

products = driver.find_elements(By.CLASS_NAME,'card')
for c in products:
    title = c.find_element(By.CLASS_NAME,'card-title')
    price = c.find_element(By.CLASS_NAME,'card-text')
    img = c.find_element(By.TAG_NAME,'img')

    print(title.text)
    print(price.text)
    print(img.get_attribute('src'))


driver.quit()