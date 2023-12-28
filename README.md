## 📌 Introduction
![image](https://github.com/choiyukyung/Capstone1_class7_team4/assets/80468377/dc97daeb-a7d0-4205-a781-5b77604d78da)

### ◾ Purpose

## 📌 Key Features
### 🔎 사용자 소비 분석
: 사용자의 결제 내역을 Fetch해서 소비 내역을 분석 </br></br>

### 🔎 맞춤형 미션 
: 사용자의 소비 분석 결과와 설문 조사 내용을 토대로 사용자 맞춤형 미션을 제공 </br></br>

### 🔎 소비 습관 피드백 & 미션 수행 조언
: 사용자의 소비 습관에 대한 피드백과 함께 미션 항목을 어떻게 절약할 수 있을지 창의적인 조언을 제공</br></br>

### 🔎 금고
: 가상 계좌에 줄여야 할 미션 금액만큼 입금해야 미션 시작 가능 
- 미션에 성공한다면 해당 금액을 바로 돌려주지만 실패한다면 돈을 2주 뒤에 돌려줌
- 사용자가 미션을 실패하더라도 그 기간동안 강제적으로 그 금액만큼을 쓰지 못하게 함
- 실천력이 부족한 사람도 저절로 절약 습관이 생길 수 있도록 도와주는 장치 </br></br>

### 🔎 캐릭터 성장
: 미션을 성공함에 따라 사용자의 레벨이 높아지고 호칭이 달라지며 캐릭터가 성장 
![스크린샷(72)](https://github.com/choiyukyung/Capstone1_class7_team4/assets/80468377/f5361d6c-4800-42a6-92a7-e662cc42ca1d)
</br></br>
### 🔎 랭킹
</br></br>
### 🔎 통계
: 사용자의 소비 분석 결과를 시각화</br></br>
### 🔎 웹앱
</br></br>

## 📌 Team Members
김민경, 중앙대학교 소프트웨어학부 21, **Team Leader, Back-End, AI** </br>
이승아, 중앙대학교 소프트웨어학부 20, **Front-End** </br>
최유경, 중앙대학교 소프트웨어학부 21, **Back-End, AI** </br>

## 📌 Start Guide

### ◾ How To Execute
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

### ◾ How To Use Test Data
1. 테스트 결제내역을 이용해 실행해보고 싶다면 먼저 회원가입을 진행합니다.
2. 브라우저의 주소창에 ```localhost:8080/payment/1/가입한유저아이디``` 라고 입력하면 임의로 만들어 놓은 토스 가상계좌의 테스트 데이터가 db에 저장되어 로그인 될 때 해당 결제 내역이 분석됩니다.
3. 다시 로그인 한 후 설문조사를 진행하면 됩니다.

### ◾ project manual
[project manual.pdf](https://github.com/choiyukyung/Capstone1_class7_team4/files/13743405/project.manual.pdf)


## System Architecture

