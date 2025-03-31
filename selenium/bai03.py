from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import time
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec

service = Service(executable_path='.venv/chromedriver.exe')
driver = webdriver.Chrome(service=service)
driver.get('https://dhthanhit.pythonanywhere.com/')


products = driver.find_elements(By.CLASS_NAME,'card')
names = []
productUrls = []
for c in products:
    names.append( c.find_element(By.CLASS_NAME,'card-title').text)
    productUrls.append(c.find_element(By.CSS_SELECTOR,'a.btn.btn-primary').get_attribute('href'))

for n, u in zip(names,productUrls):
    print(n)
    print(u)
    driver.get(u)

    comments = WebDriverWait(driver,10).until(
        ec.presence_of_all_elements_located((By.CSS_SELECTOR, '#comments li p'))
    )
    for m in comments:
        print(m.text)

# print(productUrls)

driver.quit()