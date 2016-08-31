from py_mentat import *
# from py_post import *

def get_roll_info() :
   var,ur_var,lr_var=[],[],[]
   u_roll_no = py_get_int("u_roll_no")
   var.append(u_roll_no)
   l_roll_no = py_get_int("l_roll_no")
   var.append(l_roll_no)
   urcrown=py_get_float("urcrown")
   var.append(urcrown)
   lrcrown=py_get_float("lrcrown")
   var.append(lrcrown)   
   rlen=py_get_float("roll_length")
   var.append(rlen) 
   for i in range(1,u_roll_no+1) :
   	   uname="ur_"+str(i)+"_diameter"
   	   ur_dia = py_get_float(uname)
   	   ur_var.append(ur_dia)
   	   # print uname,ur_var
   for i in range(1,l_roll_no+1) :
   	   lname="lr_"+str(i)+"_diameter"
   	   lr_dia = py_get_float(lname)
   	   lr_var.append(lr_dia)	   
   	   # print lname,lr_var

   return var,ur_var,lr_var

def generate_roll_crwon(x,y,z,l,d,c) :
# x : X coordinate of Center of roll    
# y : Y coordinate of Center of roll    
# z : Z coordinate of Center of roll    
# l : Length of roll    
# d : Diameter of roll
# c : Crown of roll    
   py_send("*origin %f %f %f "%(x,y,z))
   py_send("*add_curves ")
   py_send("%f/2 -1000 0.0 " %d)
   py_send("%f/2 %f+1000 0.0 " %(d,l))
   py_send("%f/2+%f %f/2 0.0 " %(d,c,l))
   py_send("*revolve_curves all_visible ")
   py_send("*store_surfaces upper_roll_s all_visible ")



def get_cen_coord(rname) :
    
    
    return x,y,z

def redefine_roll(var,ur_dia,lr_dia) :
#
#  Redefine the roll with crown Value
#
   l = var[4]
   uc = var[2]
   lc = var[3]
   if (abs(lc) <= 1.0e-10 or abs(uc) < 1.0e-10):
     for i in range(1,var[0]+1) :
       rname="UpperRoll_"+str(i)
       print "Roll Name :",rname
       py_send("*select_contact_body %s " %rname)  	   	 
       py_send("*visible_selected ")              
       print "Flip Surfaces with ",uc              
       py_send("*flip_surfaces all_visible ")
   	   
   if (abs(uc) > 1.0e-10):
     print l,uc,lc
     py_send("*system_reset *system_rotate 90,0,0")
     py_send("*set_curve_type arc_ppp")
     for i in range(1,var[0]+1) :
       rname="UpperRoll_"+str(i)
       print "Roll Name :",rname
       x = py_get_float("cbody_par(%s,cx)" %rname)
       y = py_get_float("cbody_par(%s,cy)" %rname)
       z = py_get_float("cbody_par(%s,cz)" %rname)
       d = ur_dia[i-1]
       print x,y,z,l,d,uc
       py_send("*select_clear ")
       py_send("*select_contact_body %s " %rname)  	   	 
       py_send("*visible_selected ")       
       py_send("*remove_surfaces all_visible ")
       generate_roll_crwon(x,y,z,l,d,uc)
       py_send("*edit_contact_body %s " %rname)
       py_send("*add_contact_body_surfaces all_visible ")
       if uc < 0.0 :
         print "Flip Surfaces with ",uc              
         py_send("*flip_surfaces all_visible ")
     py_send("*system_reset ")
   else :
     for i in range(1,var[0]+1) :
       rname="UpperRoll_"+str(i)
       print "Roll Name :",rname
       py_send("*select_contact_body %s " %rname)  	   	 
       py_send("*visible_selected ")              
       print "Flip Surfaces with ",uc              
       py_send("*flip_surfaces all_visible ")
   if (abs(lc) > 1.0e-10):
     print l,uc,lc
     py_send("*system_reset *system_rotate 90,0,0")
     py_send("*set_curve_type arc_ppp")
   	   
   #  print " No change in Roll Geometry with ",uc	   
     for i in range(1,var[1]+1) :
       rname="LowerRoll_"+str(i)
       print "Roll Name :",rname
       x = py_get_float("cbody_par(%s,cx)" %rname)
       y = py_get_float("cbody_par(%s,cy)" %rname)
       z = py_get_float("cbody_par(%s,cz)" %rname)
       d = lr_dia[i-1]
       print x,y,z,l,d,lc
       py_send("*select_clear ")
       py_send("*select_contact_body %s " %rname)  	   	 
       py_send("*visible_selected ")       
       py_send("*remove_surfaces all_visible ")
       generate_roll_crwon(x,y,z,l,d,lc)
       py_send("*edit_contact_body %s " %rname)
       py_send("*add_contact_body_surfaces all_visible ")
       if lc < 0.0 :
         print "Flip Surfaces with ",lc              
         py_send("*flip_surfaces all_visible ")     
     py_send("*system_reset ")     
   else :
     for i in range(1,var[1]+1) :
       rname="LowerrRoll_"+str(i)
       print "Roll Name :",rname
       py_send("*select_contact_body %s " %rname)  	   	 
       py_send("*visible_selected ")              
       print "Flip Surfaces with ",lc              
       py_send("*flip_surfaces all_visible ")
     py_send("*select_clear *invisible_selected " )


   
def main():
   var,ur_dia,lr_dia=get_roll_info() 
   print "\n Start Redefine Roll Crown \n"
   print "Rolls variables ",var
   print "Upper Rolls variables ",ur_dia
   print "Lower Rolls variables ",lr_dia
   py_send("*py_echo off *set_undo off ")
   
   redefine_roll(var,ur_dia,lr_dia)
   py_send("*py_echo on *set_undo on")   
   print "\n Complete Redefine Roll Crown \n"
     
if __name__ == '__main__':
    main()
