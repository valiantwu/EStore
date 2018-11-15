if not exist C:\evn-dev md C:\evn-dev
if not exist C:\evn-dev\mservice\lib\trd md c:\evn-dev\mservice\lib\trd
if not exist C:\evn-dev\mservice\lib\core md c:\evn-dev\mservice\lib\core
if not exist C:\evn-dev\web\lib\trd md c:\evn-dev\web\lib\trd
if not exist C:\evn-dev\web\lib\core md c:\evn-dev\web\lib\core


#del /Q C:\evn-dev\mservice\*
#del /Q C:\evn-dev\web\*


xcopy /Y /E dev-env\* C:\evn-dev\