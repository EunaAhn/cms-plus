# CMS+ (Hyosung CMS+) 프로젝트
![view-dashboard](https://github.com/user-attachments/assets/f836c6a8-bdfb-4b01-9a20-2cd475317bc2)

## 프로젝트 개요
CMS+ (Hyosung CMS+)는 자동 청구 및 결제 관리를 위한 통합 플랫폼입니다. 이 프로젝트는 사용자 친화적인 인터페이스를 통해 청구서 생성, 결제 처리, 회원 관리 등의 기능을 제공합니다.

## 주요 기능
1. 회원 관리![view-member](https://github.com/user-attachments/assets/6942228a-dd58-4a7e-8973-893d1e5a7586)

   - 회원 등록, 조회, 수정, 삭제 기능
   - 회원 정보 엑셀 일괄 등록 기능
   - 회원별 청구 내역 및 계약 정보 조회
     
2. 계약 관리![view-contract](https://github.com/user-attachments/assets/242ade46-f46f-4bee-8b6a-e0d2babbe5d4)

   - 계약 생성, 조회, 수정, 삭제 기능
   - 계약별 상품 관리 및 가격 설정
   - 계약 기간 및 갱신 관리
     
3. 청구서 생성 및 관리![view-billing](https://github.com/user-attachments/assets/5cc6b9d8-a09c-492a-86b3-d64639f59dfd)

   - 자동 청구서 생성 기능
   - 청구서 상태 관리 (작성중, 청구완료, 입금대기, 입금완료 등)
   - 청구서 발송 기능 (이메일, SMS)
   
4. 다양한 결제 방식 지원 (카드, 계좌이체, 가상계좌 등)
   
5. 간편 동의 프로세스![view-setting](https://github.com/user-attachments/assets/1df18ecf-f306-4592-82d5-82228f615ac1)

   - QR 코드를 통한 간편 동의 링크 생성
   - 모바일 기기를 통한 간편한 동의 절차
   - 전자 서명 기능
     
6. 통계 (계약 갱신 에측)![view-statistics](https://github.com/user-attachments/assets/579efac9-c7fd-4991-bad1-d294aa97bfe5)

   - 계약 갱신율 예측 기능

## 기술 스택
![Untitled (7)](https://github.com/user-attachments/assets/820221f4-9451-4c0b-87c6-ca123dd4a42d)

### 백엔드
- Java
- Spring Boot
- JPA / Hibernate
- MySQL
- Redis
- Kafka

### 프론트엔드
- React
- Tailwind CSS

### 인프라 및 도구
- AWS (EC2, RDS, S3)
- Docker
- Jenkins (CI/CD)

### 배포서버

https://www.hyosungcmsplus.site


### 디렉터리 구조

```
📁 cms
 ├──── 📁 .github
 │      ├──── 📁 ISSUE_TEMPLATE
 │      │      ├──── 📄 BUILD.md
 │      │      ├──── 📄 CICD.md
 │      │      ├──── 📄 DOCS.md
 │      │      ├──── 📄 FEAT.md
 │      │      ├──── 📄 FIX.md
 │      │      ├──── 📄 PERF.md
 │      │      ├──── 📄 REFACTOR.md
 │      │      ├──── 📄 STYLE.md
 │      │      └──── 📄 TEST.md
 │      └──── 📁 workflows
 │             ├──── 📄 ANALYSIS_CICD.yml
 │             ├──── 📄 BATCH_CICD.yml
 │             ├──── 📄 DEV_TRIGGER.yml
 │             ├──── 📄 FE_CD.yml
 │             ├──── 📄 FE_CI.yml
 │             ├──── 📄 FEAT_TRIGGER.yml
 │             ├──── 📄 IMAGE_RESIZE.yml
 │             ├──── 📄 MAIN_CICD.yml
 │             ├──── 📄 MESSAGING_CICD.yml
 │             ├──── 📄 PAYMENT_CICD.yml
 │             └──── 📄 PR_ALARM.yml
 ├──── 📁 client
 │      ├──── 📁 public
 │      │      └──── 📄 default_excel.xlsx
 │      │──── 📁 src
 │      │      ├──── 📁 apis
 │      │      ├──── 📁 assets
 │      │      ├──── 📁 components
 │      │      ├──── 📁 hooks
 │      │      ├──── 📁 labs
 │      │      ├──── 📁 pages
 │      │      ├──── 📁 routes
 │      │      ├──── 📁 stores
 │      │      ├──── 📁 styles
 │      │      ├──── 📁 utils
 │      │      ├──── 📄 App.jsx
 │      │      └──── 📄 main.jsx
 │      ├──── 📄 .env
 │      ├──── 📄 .eslintrc.cjs
 │      ├──── 📄 index.html
 │      ├──── 📄 jsconfig.json
 │      ├──── 📄 postcss.config.js
 │      ├──── 📄 tailwind.config.js
 │      └──── 📄 vite.config.js
 ├──── 📁 convention
 │      ├──── 📄 CATALOG
 │      └──── 📄 COMMIT_LINT
 ├──── 📁 database
 │      ├──── 📄 ddl.txt
 │      ├──── 📄 Dockerfile
 │      └──── 📄 ini.sql
 ├──── 📁 DEPLOY_FILE
 │      ├──── 📁 client
 │      └──── 📁 server
 ├──── 📁 DEPLOY_LOG
 │      ├──── 📁 client
 │      └──── 📁 server
 ├──── 📁 infra
 │      ├──── 📄 lambda.py
 │      ├──── 📄 request.zip
 │      ├──── 📄 task-definition-analysis.json
 │      ├──── 📄 task-definition-batch.json
 │      ├──── 📄 task-definition-main.json
 │      ├──── 📄 task-definition-messaging.json
 │      └──── 📄 task-definition-payment.json
 ├──── 📁 server
 │      ├──── 📁 config
 │      ├──── 📁 log
 │      ├──── 📁 src
 │      │      ├──── 📁 docs
 │      │      │      └──── 📁 asciidoc
 │      │      ├──── 📁 main
 │      │      │      │──── 📁 generated
 │      │      │      │──── 📁 java
 │      │      │      │      │──── 📁 config
 │      │      │      │      │──── 📁 domain
 │      │      │      │      └──── 📁 util
 │      │      │      └──── 📁 resources
 │      │      └──── 📁 test
 │      └──── 📄 Dockerfile
 ├──── 📁 server-analysis
 │      └──── 📁 src
 │      │      ├──── 📁 data
 │      │      ├──── 📁 notebooks
 │      │      ├──── 📄 server.py
 │      │      └──── 📄 trigger.py
 │      ├──── 📄 Dockerfile.web
 │      ├──── 📄 jupyter-api.code-workspace
 │      ├──── 📄 Pipfile
 │      ├──── 📄 Procfile
 │      ├──── 📄 run.sh
 │      └──── 📄 requirements.txt
 ├──── 📁 server-batch
 ├──── 📁 server-kafka
 │      └──── 📄 docker-compose.yml
 ├──── 📁 server-messaging
 ├──── 📁 server-monitoring
 │      ├──── 📁 elasticsearch
 │      ├──── 📁 kibana
 │      ├──── 📁 logstash
 │      ├──── 📁 prometheus
 │      └──── 📄 docker-compose.yml
 ├──── 📁 server-payment
 └──── 📄 docker-compose.yml
```

- .github: 이슈 템플릿, 워크플로우 파일들
- client: 리액트 앱
- convention: 협업 세팅 문서들
- database: 데이터베이스 파일들
- DEPLOY_FILE: 배포 시 배포용 파일들
- DEPLOY_LOG: 배포 시 배포내역 로그들
- infra: 기타 AWS 인프라 설정에 필요한 파일들
- server: 메인 서비스
- server-analysis: 통계 서버
- server-batch: 배치 서버
- server-kafka: 카프카 서버
- server-messaging: 메시징 서버
- server-payment: 결제 서버
- docker-compose.yml: 로컬에서 사용하는 공동 세팅
