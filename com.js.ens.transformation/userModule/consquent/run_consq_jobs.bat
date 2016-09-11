cd f1
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f0.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f1.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f1_job1 -b n -v n
cd ..
cd f2
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f2.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f2_job1 -b n -v n
cd ..
cd f3
rem call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f3.proc
rem call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f3_job1 -b n -v n
cd ..
cd f4
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f4.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f4_job1 -b n -v n
cd ..
cd f5
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f5.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f5_job1 -b n -v n
cd ..
cd f6
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f6.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f6_job1 -b n -v n
cd ..
cd f7
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f7.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f7_job1 -b n -v n
cd ..