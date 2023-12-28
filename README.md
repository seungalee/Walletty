## 📌 Introduction
![image](https://github.com/choiyukyung/Capstone1_class7_team4/assets/80468377/dc97daeb-a7d0-4205-a781-5b77604d78da)


## 📌 Team Members
김민경, 중앙대학교 소프트웨어학부 21, **Team Leader, Back-End, AI** </br>
이승아, 중앙대학교 소프트웨어학부 20, **Front-End** </br>
최유경, 중앙대학교 소프트웨어학부 21, **Back-End, AI** </br>

## 📌 Start Guide

### ◾ How to Execute
1. code -> download ZIP으로 파일을 다운 받은 후 압축 풀기
2. demo -> build.gradle 파일을 선택한 후 IntelliJ IDEA를 이용해 파일 열기
3. mySQL workbench에서 'capstone1'이라는 이름으로 database 생성  
	```create database capstone1; ```
4. User 'host'@'localhost’생성하고 비밀번호 1234로 설정  
	```create user 'host'@'localhost' identified by '1234';```
5. 위의 user에게 해당 DB에 대해 모든 권한 부여  
	```grant all privileges on capstone1.* to 'host'@'localhost';```  
	```flush privileges;```
6. IntelliJ에서 DemoApplication 실행
7. IntelliJ 터미널에서 경로를 demo\src\main\frontend로 설정한 후 npm i
8. npm start  

- 참고사항: 해당 프로젝트가 github에 public으로 올려져있기 때문에 Chatgpt API Access Token이 자동으로 만료됐습니다. 이로 인해 피드백과 미션 로직이 제대로 동작하지 않을 수 있습니다.

### ◾ Use Test Data
1. 테스트 결제내역을 이용해 실행해보고 싶다면 먼저 회원가입을 진행합니다.
2. 브라우저의 주소창에 ```localhost:8080/payment/1/가입한유저아이디``` 라고 입력하면 임의로 만들어 놓은 토스 가상계좌의 테스트 데이터가 db에 저장되어 로그인 될 때 해당 결제 내역이 분석됩니다.
3. 다시 로그인 한 후 설문조사를 진행하면 됩니다.

### ◾ project manual
[project manual.pdf](https://github.com/choiyukyung/Capstone1_class7_team4/files/13743405/project.manual.pdf)
