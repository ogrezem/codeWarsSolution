@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  codeWarsSolution startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and CODE_WARS_SOLUTION_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\codeWarsSolution.jar;%APP_HOME%\lib\guava-23.0.jar;%APP_HOME%\lib\sdk-1.0.2.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\lombok-1.18.6.jar;%APP_HOME%\lib\spring-boot-starter-data-mongodb-2.1.4.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-web-2.1.4.RELEASE.jar;%APP_HOME%\lib\JDA-4.ALPHA.0_82.jar;%APP_HOME%\lib\spring-boot-devtools-2.1.4.RELEASE.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\error_prone_annotations-2.0.18.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.14.jar;%APP_HOME%\lib\spring-boot-starter-json-2.1.4.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.1.4.RELEASE.jar;%APP_HOME%\lib\mongodb-driver-3.8.2.jar;%APP_HOME%\lib\spring-data-mongodb-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.1.4.RELEASE.jar;%APP_HOME%\lib\hibernate-validator-6.0.16.Final.jar;%APP_HOME%\lib\spring-webmvc-5.1.6.RELEASE.jar;%APP_HOME%\lib\spring-web-5.1.6.RELEASE.jar;%APP_HOME%\lib\annotations-16.0.1.jar;%APP_HOME%\lib\async-http-client-2.8.1.jar;%APP_HOME%\lib\spring-data-commons-2.1.6.RELEASE.jar;%APP_HOME%\lib\async-http-client-netty-utils-2.8.1.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.1.4.RELEASE.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.11.2.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.26.jar;%APP_HOME%\lib\slf4j-api-1.7.26.jar;%APP_HOME%\lib\nv-websocket-client-2.5.jar;%APP_HOME%\lib\okhttp-3.13.0.jar;%APP_HOME%\lib\opus-java-1.0.4.pom;%APP_HOME%\lib\commons-collections4-4.3.jar;%APP_HOME%\lib\trove4j-3.0.3.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.9.8.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.9.8.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.9.8.jar;%APP_HOME%\lib\jackson-databind-2.9.8.jar;%APP_HOME%\lib\log4j-core-2.11.2.jar;%APP_HOME%\lib\commons-text-1.6.jar;%APP_HOME%\lib\commons-lang3-3.8.1.jar;%APP_HOME%\lib\commons-io-2.6.jar;%APP_HOME%\lib\httpmime-4.5.8.jar;%APP_HOME%\lib\httpclient-4.5.8.jar;%APP_HOME%\lib\log4j-api-2.11.2.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.1.4.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.1.4.RELEASE.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\spring-tx-5.1.6.RELEASE.jar;%APP_HOME%\lib\spring-context-5.1.6.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.1.6.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.1.6.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.1.6.RELEASE.jar;%APP_HOME%\lib\spring-core-5.1.6.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.23.jar;%APP_HOME%\lib\mongodb-driver-core-3.8.2.jar;%APP_HOME%\lib\bson-3.8.2.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.17.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.17.jar;%APP_HOME%\lib\tomcat-embed-el-9.0.17.jar;%APP_HOME%\lib\validation-api-2.0.1.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\classmate-1.4.0.jar;%APP_HOME%\lib\okio-1.17.2.jar;%APP_HOME%\lib\opus-java-api-1.0.4.jar;%APP_HOME%\lib\opus-java-natives-1.0.4.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\jackson-core-2.9.8.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.34.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.34.Final.jar;%APP_HOME%\lib\netty-reactive-streams-2.0.0.jar;%APP_HOME%\lib\netty-handler-4.1.34.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.34.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.34.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.34.Final.jar;%APP_HOME%\lib\reactive-streams-1.0.2.jar;%APP_HOME%\lib\javax.activation-1.2.0.jar;%APP_HOME%\lib\httpcore-4.4.11.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\spring-jcl-5.1.6.RELEASE.jar;%APP_HOME%\lib\jna-4.5.2.jar;%APP_HOME%\lib\netty-codec-dns-4.1.34.Final.jar;%APP_HOME%\lib\netty-codec-4.1.34.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.34.Final.jar;%APP_HOME%\lib\netty-transport-4.1.34.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.34.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.34.Final.jar;%APP_HOME%\lib\netty-common-4.1.34.Final.jar;%APP_HOME%\lib\logback-core-1.2.3.jar

@rem Execute codeWarsSolution
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CODE_WARS_SOLUTION_OPTS%  -classpath "%CLASSPATH%" ru.ogrezem.codeWarsSolution.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CODE_WARS_SOLUTION_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CODE_WARS_SOLUTION_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
