cd f2
call C:\MSC.Software\Marc_Classic\2014.2.0\mentat2014.2\bin\mentat.bat -pr 00_main_%model%_gen_f2.proc
call C:\MSC.Software\Marc_Classic\2014.2.0\marc2014.2\tools\run_marc -jid %model%_f2_job1 -b n -v n
cd ..
