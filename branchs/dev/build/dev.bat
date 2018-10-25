if not exist C:\evn-dev md C:\evn-dev
if not exist C:\evn-dev\trd md c:\evn-dev\trd
if not exist C:\evn-dev\corelib md c:\evn-dev\corelib


#del /Q C:\evn-dev\trd\*
#del /Q C:\evn-dev\corelib\*


xcopy /Y /E dev-env\* C:\evn-dev\