# Android_Hay

제작 : 3조 김태현, 최현아, 최지석, 정영재, 이민우

사용법

1. 클론한 DB를 본인 MySQL에 Import합니다. 

1-1. Schema Name : mypeople / Tables : friendslist, tag, user

2. 클론한 JSP폴더 안의 파일들을 본인의 Tomcat-webapps-ROOT경로로 들어가서 mypeople폴더를 생성 후 그 안에 옮깁니다.

3. 클론받은 Android_Hay폴더 속에 있는 Hay폴더를 본인 Android Studio 기본 설정 경로에 넣어줍니다.

4. Android Studio를 실행한 후 기본 설정 경로에 넣은 Hay폴더를 엽니다.

5. 태현형 여기에 적어주세요

6. layout 중 activity_login.xml를 열어 

<EditText

        android:id="@+id/edt_ip"
        
        android:layout_width="match_parent"
        
        android:layout_height="0px"
        
        android:textSize="20dp"
        
        android:gravity="center"
        
        android:text="xxx.xxx.xxx" <<<<<< 이 부분을 본인의 ip로 바꿔줍니다.
        
        android:hint="Apache Server IP Address"
        
        android:visibility="invisible"
        
        />
        
7. 구동시켜 확인해봅니다. (에뮬레이터 언어를 한국어를 1순위로 바꿔주면 더 다양한 기능이 구현됩니다)

----

----

----

----

----

----


----------작업자 유의사항----------

Android_Hay

-수정 2020.12.31 01:14-

필독

0.무조건 클론 받은 파일로만 작업하세요!!!!!!!

1.공용 부분 수정은 미리 말씀해주세요. (Bean, Adapter 등..)

2.만약 한 클래스를 협업해야 하는 경우 아래의 주석을 이용해 구간을 나누고 작업하세요. (작업량 구간도 나누면 좋습니다)

////////////////////////////////////////////////////////////
//                                                        //
//                                                        //
//                       구분용 주석                       //
//                                                        //
//                                                        //
////////////////////////////////////////////////////////////

3.Activity 추가 및 삭제와 같이 '프레임'을 건드리는 작업은 미리 말씀해주세요. (애니메이션, 드로어블 등도 포함..)

4.Class_xxx는 개인적으로 사용하는 Method 저장소입니다. 자신의 영역을 지켜주세요.

5.MainActivity(주소록 띄우는 곳)가 완성되지 않았는데 연계해서 작업해야 하는 경우, 임의의 값을 넘겨서 테스트해주세요. (수정, 삭제 등..)

6.Activity 생성 시 양식 => 역할 + Acitity => Login하는 Activity의 경우 LoginActivity

7.Layout 생성 시 양식 => activity + 역할 => LoginActivity의 layout 경우 activity_login.xml

8.각 layout.xml속에서 사용되는 id 양식 => 해당acitivy + 기능 + 부여할 이름 => activity_login.xml의 로그인하는 버튼일 경우 login_Btn_login

8-1.기능명은 각자 쓰되, 누구나 알아볼 수 있게 작성바랍니다.

8-2.항상 자기가 선언한 id가 맞는지 확인하고 사용하시길..

9.화이팅

