# Android_Hay

제작 : 3조 김태현, 김현아, 최지석, 정영재, 이민우

사용법

1. 클론한 DB를 본인 MySQL에 Import합니다.
2. 클론한 JSP폴더 안의 파일들을 본인의 Tomcat-webapps-ROOT경로로 들어가서 mypeople폴더를 생성 후 그 안에 옮깁니다.
3. 안드로이드 스튜디오를 실행시켜 클론받은 Android_Hay폴더 속에 있는 Hay폴더를 열어줍니다.
4. layout 중 activity_login.xml를 열어 

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
        
5. 구동시켜 확인해봅니다.

