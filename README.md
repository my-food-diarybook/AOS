# 식사일기 AOS
![image](https://github.com/my-food-diarybook/AOS/assets/85734140/8de369ac-4d0c-4535-bf8a-c960f5653266)
- 오늘 먹은 음식을 사진으로 기록하고, 카테고리별로 확인할 수 있는 기능을 제공
- [개발 Log](https://www.notion.so/Android-2c7707ec50a2425b90d04715d1b93251)
## Stack
<div align=start> 
  <img src="https://img.shields.io/badge/android-3DDC84?style=for-the-badge&logo=android&logoColor=white"> 
  <img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white">
  <img src="https://img.shields.io/badge/Jetpack Compose-67C52A?style=for-the-badge&logo=jetpackcompose&logoColor=white"> 
</div>
- MVVM, Paging, Room, Multi Module


## 구현 체크리스트

### [구현]
- [로그인/회원가입]
  - 로그인 기능
  - 회원가입
- [홈]
  - 현재 캘린더 변경
  - 캘린더 데이터 불러오기
  - 사진 촬영 /이미지 업로드
- [홈 데이]
  - 홈 데이 데이터 불러오기
  - 홈 데이에서 업로드 기능
- [상세]
  - 사진 수정, 추가, 메모 수정, 일기 삭제 완료
  - 데이터 불러오기
  - 장소 설정하기 기능 (일부 수정 필요)
- [마이]
  - 공지사항 기능 & ui 완료
  - 모든 ui 완료

### [미구현]
- [로그인/회원가입]
  - 소셜 로그인(ui 완료)
  - 비밀번호 찾기(ui 완료)
- [홈 데이]
  - 상단 탭 클릭으로 이전 or 다음 기록으로 이동
- [타임라인]
  - ui, 기능 완료 -> api 연동 필요
- [검색]
  - ui 완료 -> 기능 구현,api 연동 필요
- [상세]
  - 장소 설정하기 페이징 처리
- [마이]
  - 마이 api 연동 필요
  - 모든 식사일기 삭제 기능 구현 (ui 완료)
  - 로그아웃 api 연동 필요
  
### [Fix 요청]
- 서버 데이터 로딩 화면
- home day 탑 바 겹침 수정
