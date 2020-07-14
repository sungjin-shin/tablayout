Week1 조호연, 신성진 project 설명
======================
1. 팀원: 조호연, 신성진

2. 프로젝트 이름: 마이앱

3. 프로젝트 내용

(1) 탭 구현
  - 탭 3개를 생성할 MainActivity와 tabactivity_main.xml 파일 형성.
  - VPAdapter를 만들어 각 fragment를 하나로 연결.
  - 각 tab 버튼과 viewPager를 연결하여 버튼과 화면이 동기화되게 함.
  - 각 tab의 viewpage에 대한 fragment와 3개의 탭들의 xml 파일을 연결한 후 3개의 프로젝트에 대해 각각 작성한 activity 코드를 fragment의 형태에 맞게 수정.
  - font를 설치하여 앱의 모든 레이아웃에 적용.
    
(2) 전반적인 앱의 기능

<1번 탭 - 연락처>
  - 연락처 - JSON 파일에 저장된 정보 파싱, ListView를 이용하여 화면에 띄움
  - editSearch와 searchAdapter를 만들어 연락처 검색 기능 구현 (이름 또는 전화번호로 검색 가능)
  - button, AlertDialog와 Floatingactionbutton을 이용한 연락처 추가, LongClickListener로 인식한 신호로 연락처 삭제 기능 구현 (JSON 파일이라 일시적 저장만 가능함)
  - button과 intent를 이용하여 '전화번호를 다이얼로 바로 이동', '전화번호로 바로 전화' 기능 구현

<2번 탭 - 갤러리>
  - 갤러리 - RecyclerView와 ViewPager를 이용하여 구현
  - 메인 탭의 fragment와 연결되는 RecyclerViewFragment.java에서 recyclerview (gridLayoutManager)를 형성 후 galleryAdapter와 연결
  - 사진 슬라이드 및 확대, 축소 가능 (축소 시 pointerIndex out of range)
  - 위 에러 해결 위해 ViewPagerFixed를 implement하여 exception을 모두 무시하려 했으나, 연속으로 축소하는 경우 여전히 에러 발생
  - Internet Permission 허용을 이용해, url에서 20개의 image를 load하여 갤러리에 띄움.
  
<3번 탭 - 음악 플레이어>
  - 음악 플레이어 - raw에 저장된 mp3 파일들을 startService, stopService를 이용하여 재생, 정지함. mediaPlayer의 start(), stop(), pause()를 이용해서 background에서도 음악이 재생될 수 있 게 함.  
  - 음악들의 list가 listview로 연속적으로 나타나며, 이에 대한 adpater가 구현되어 있음. 
  - 각 음악이 하나의 activity(musicService)에 해당되어, intent를 사용해 음악 이름, 음악 사진 등을 서로 전달
  - OnclickListener를 활용하여 여러가지 버튼 구현 및, listview에서 클릭시 mp3 player로 넘어가도록 설정.
  - 음악 검색 가능, 재생, 일시정지, 정지 기능 및 음악 재생 현황을 나타내는 바 구현 ( seekBar 와 mediaplayer.getCurrentPosition() 을 이용해 구현함.)
