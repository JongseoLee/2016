#
#
#  This Program is to change the MARC input for applying temperature profile.
#
#

from py_mentat import *
from py_post import *

def get_fname():  # get file name
   fname = py_get_string("model_name()")
   iname = fname+"_"+py_get_string("job_name()")+".dat"
   #   print "File names ==",fname,iname
   return fname,iname

def change_file(fname,iname) :
   oname="temp.dat"
   rf=open(iname,"r")
   wf=open(oname,"w")
   s = rf.read()
   no_lines=s.count('\n')
   print (" number of lines ",no_lines)
   rf.seek(0) # move to first line
   k=1
   while k<= no_lines : 
     s1= rf.readline()
     wf.writelines(s1)
     if s1[0:12] == "change state" : 
        print "++++++ started change input ++++++"
        j=1
        while j <= 4 :
          s1= rf.readline()
          wf.writelines(s1)
          j=j+1
        dummy= rf.readline()
        s1="         3 \n"
        wf.writelines(s1)
        dummy= rf.readline()        
        s1="         1 \n"
        wf.writelines(s1) 
        k=k+5 
     k=k+1   
   print "++++++ Finished change input ++++++"
   rf.close() 
   wf.close() 
   move_file="*system_command move temp.dat "+iname
   py_send(move_file)
   return 

def main():   
   fname,iname = get_fname()
   change_file(fname,iname)
   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
    py_disconnect()






