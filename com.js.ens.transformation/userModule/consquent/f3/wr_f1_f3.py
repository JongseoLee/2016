#
#
from py_mentat import *
from py_post import *



def main():   
   wr_crown=py_get_float("wr_crown") 
   print "Work Roll Crown is ",wr_crown 
   if abs(wr_crown) <= 0.0000000001 : 
      py_send("*exec_proc wr_f1_f3.proc ")
      print " Work Roll Crown is not applied "
   elif wr_crown > 0.0000000001 :
      py_send("*exec_proc wr_f1_f3_pcrown.proc ")
      print " Plus Work Roll Crown is applied "
   elif wr_crown < -0.0000000001 :
      py_send("*exec_proc wr_f1_f3_mcrown.proc ")
      print " Minus Work Roll Crown is applied "
   else :
      print " Crown is not detected "   
   print " Finished Work Roll Crown Definition "
   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
    py_disconnect()






