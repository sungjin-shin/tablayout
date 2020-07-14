Week1 조호연, 신성진 project 설명
1. 탭 구현
  a. 탭 3개를 생성할 MainActivity와 tabactivity_main.xml 파일 형성, 각 tab 버튼과 viewPager를 연결하여 버튼과 화면이 동기화되게 함.
  b. 각 tab의 viewpage에 대한 fragment와 activity_main.xml 파일을 연결한 후 3개의 프로젝트에 대해 각각 작성한 activity 코드를 fragment의 형태에 맞게 수정
2. 전반적인 앱의 기능
  a. 연락처 - JSON 파일에 저장된 정보 파싱, ListView를 이용하여 화면에 띄움
  → 연락처 검색 (이름 또는 번호), 연락처 추가, 삭제 (JSON 파일이라 일시적 저장)
  
  b. 갤러리 - RecyclerView와 ViewPager를 이용하여 구현
  → 사진 슬라이드 및 확대, 축소 가능 (축소 시 pointerIndex out of range)
  → 위 에러 해결 위해 ViewPagerFixed를 implement하여 exception을 모두 무시하려 했으나, 연속으로 축소하는 경우 여전히 에러 발생
  
  c. 음악 플레이어 - raw에 저장된 mp3 파일들을 startService, stopService를 이용하여 재생, 정지함. mediaPlayer의 start(), stop(), pause()를 이용해서 background에서도 음악이 재생될 수 있게 함.  
   음악 검색 가능, 재생, 일시정지, 정지 기능 및 음악 재생 현황을 나타내는 바 구현 ( seekBar 와 mediaplayer.getCurrentPosition() 을 이용해 구현함.)
