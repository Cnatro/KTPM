from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import time

service = Service(executable_path='.venv/chromedriver.exe')
driver = webdriver.Chrome(service=service)
driver.get('https://dhthanhit.pythonanywhere.com/')

names = []
urls = []
categories = driver.find_elements(By.CSS_SELECTOR,'.navbar-nav li')[1:-3]
for c in categories:
    names.append(c.text)
    urls.append(c.find_element(By.TAG_NAME,'a').get_attribute('href'))


for n, u in zip(names,urls):
    print("================")
    print(n)
    driver.get(u)
    time.sleep(2)

    products = driver.find_elements(By.CLASS_NAME,'card')
    for c in products:
        title = c.find_element(By.CLASS_NAME, 'card-title')
        price = c.find_element(By.CLASS_NAME, 'card-text')
        img = c.find_element(By.TAG_NAME, 'img')

        print(title.text)
        print(price.text)
        print(img.get_attribute('src'))

driver.quit()