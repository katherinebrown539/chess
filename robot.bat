@echo off
cls

::set DRIVE_LETTER=%1:
set PATH=C:\Java\bin;C:\Java\ant-1.9.6\bin;c:\Windows

ant robot -Ddrive-letter=C