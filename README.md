## How to Execute
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

주의사항: github에 public으로 프로젝트를 올리면 chatgpt api 토큰이 자동으로 만료됩니다. 이로 인해 피드백과 미션 로직이 제대로 동작하지 않을 수 있습니다.

## 테스트 데이터 이용하기
1. 테스트데이터를 이용해 실행해보고 싶다면 먼저 회원가입을 진행합니다.
2. 브라우저의 주소창에 ```localhost:8080/payment/1/가입한유저아이디``` 라고 치면 저희가 이미 만들어 놓은 토스 가상계좌의 테스트데이터가 db에 저장되어 로그인 될 때 분석됩니다.
3. 다시 로그인 한 후 설문조사를 진행하면 됩니다.

## project manual
[project manual.pdf](https://github.com/choiyukyung/Capstone1_class7_team4/files/13743405/project.manual.pdf)
