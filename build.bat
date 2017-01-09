@echo off
cls

::set DRIVE_LETTER=%1:
set PATH=C:\Java\bin;C:\Java\ant-1.9.6\bin;c:\Windows

ant run -Ddrive-letter=C
::ant robot -Ddrive-letter=C
::ant help -Ddrive-letter=C
::ant jar -Ddrive-letter=C