from py_mentat import *
# from py_post import *

def get_motion_info() :
   mvar=[]
   u_roll_no = py_get_int("u_roll_no")
   l_roll_no = py_get_int("l_roll_no")
   URM_ent = py_get_float("URM_ent")
   URM_exit = py_get_float("URM_exit")
   URM_ts = py_get_float("URM_ts")
   URM_te = py_get_float("URM_te")
   LRM_ent = py_get_float("LRM_ent")
   LRM_exit = py_get_float("LRM_exit")
   LRM_ts = py_get_float("LRM_ts")
   LRM_te = py_get_float("LRM_te")
   mvar.append(u_roll_no)
   mvar.append(l_roll_no) 
   mvar.append(URM_ent)
   mvar.append(URM_exit)
   mvar.append(URM_ts)
   mvar.append(URM_te)      
   mvar.append(LRM_ent)
   mvar.append(LRM_exit)
   mvar.append(LRM_ts)
   mvar.append(LRM_te)      
   return mvar

def add_motion(cname,tname,val) :
    py_send("*edit_contact_body %s " %cname)
    vrot=py_get_float("cbody_par(%s,vrot)" %cname)
    print "  Defined Contact body vrot ",cname, vrot
    py_send("*contact_option control:position")    
    py_send("*cbody_param_table py %s " %tname)
    py_send("*contact_value py %s " %val)
    py_send("*contact_value prot %e " %vrot)
    py_send("*cbody_param_table prot roll_rot ")


def add_roll_rot_table() :
    py_send("*new_md_table 1 1 ")
    py_send("*set_md_table_type 1 ")
    py_send("time")
    py_send("*table_name roll_rot")
    py_send("*table_add")
    py_send("0,0 100.0,100.0 ")	  
    
   
def main():
   m_cont=py_get_string("m_control")
   print " Roll Motion is controled as ",m_cont
   if m_cont != "load" :
     mvar=get_motion_info()
     print "Motion Information for Adding Roll Motion ",mvar
     add_roll_rot_table()
# Add Upper Roll motion
     for i in range(1,mvar[0]+1) :
       cname="UpperRoll_"+str(i)
       tname="urmotion"
       val=mvar[2]+(mvar[3]-mvar[2])*((i-1.0)/(mvar[0]-1))
       print i,mvar[0],mvar[2],mvar[3],val
       add_motion(cname,tname,val)   
# Add Upper Roll motion
     for i in range(1,mvar[1]+1) :
       cname="LowerRoll_"+str(i)
       tname="lrmotion"
       val=mvar[6]+(mvar[7]-mvar[6])*((i-1.0)/(mvar[1]-1))
       print i,mvar[1],mvar[6],mvar[7],val      
       add_motion(cname,tname,val)
   else :
       print " Skipped to add_roll_movement due to already defined nodal mvement  "
       
if __name__ == '__main__':

    main()
