from py_mentat import *
# from py_post import *

def get_hd_info() :
   var=[]
# Var[0]  : Upper Roll No.
# Var[1]  : Lower Roll No.   
# Var[2]  : Holddown Roll location (upper & Lower)   
# Var[3]  : Front Hold Down Roll Diameter
# Var[4]  : Front Hold Down Roll Pitch
# Var[5]  : Front Hold Down Roll position 
# Var[6]  : Rear Hold Down Roll Diameter 
# Var[7]  : Rear Hold Down Roll Pitch
# Var[8]  : Rear Hold Down Roll position 
# Var[9]  : Roll Diameter
# Var[10] : Plate Velocity
   
   u_roll_no = py_get_int("u_roll_no")
   var.append(u_roll_no) # Var[0]
   l_roll_no = py_get_int("l_roll_no")
   var.append(l_roll_no) # Var[1]
   hd_roll_loc= py_get_string("hd_roll_loc")
   var.append(hd_roll_loc) # Var[2]
   fhd_dia = py_get_float("fhd_dia")
   var.append(fhd_dia) # Var[3]
   fhd_pitch = py_get_float("fhd_pitch")
   var.append(fhd_pitch) # Var[4]
   fhd_pos = py_get_float("fhd_pos")
   var.append(fhd_pos) # Var[5]
   rhd_dia = py_get_float("rhd_dia")
   var.append(rhd_dia) # Var[6]
   rhd_pitch = py_get_float("rhd_pitch")
   var.append(rhd_pitch) # Var[7]
   rhd_pos = py_get_float("rhd_pos")
   var.append(rhd_pos) # Var[8]
   r_dia = py_get_float("roll_dia")
   var.append(r_dia) # Var[9]
   plate_v = py_get_float("plate_v")
   var.append(plate_v) # Var[10]
   
   return var


def get_motion_info() :
   mvar=[]
   URM_ent = py_get_float("URM_ent")
   URM_exit = py_get_float("URM_exit")
   URM_ts = py_get_float("URM_ts")
   URM_te = py_get_float("URM_te")
   LRM_ent = py_get_float("LRM_ent")
   LRM_exit = py_get_float("LRM_exit")
   LRM_ts = py_get_float("LRM_ts")
   LRM_te = py_get_float("LRM_te")
   mvar.append(URM_ent)
   mvar.append(URM_exit)
   mvar.append(URM_ts)
   mvar.append(URM_te)      
   mvar.append(LRM_ent)
   mvar.append(LRM_exit)
   mvar.append(LRM_ts)
   mvar.append(LRM_te)      
   return mvar
   
def create_motion_table(mvar) :
#   print  "check in motion table creating ",mvar
   py_send("*new_md_table 1 1 ")
   py_send("*table_name urmotion ")
   py_send("*set_md_table_type 1 time ")
   py_send("*table_add ")
   py_send("0  0 ")
   py_send("%s 0 " %mvar[2])
   py_send("%s+%s 1 " %(mvar[2],mvar[3]))
   py_send("%s+%s+1.0 1 " %(mvar[2],mvar[3]))
   py_send("*new_md_table 1 1 ")
   py_send("*table_name lrmotion ")
   py_send("*set_md_table_type 1 time ")
   py_send("*table_add ")
   py_send("0  0 ")
   py_send("%s 0 " %mvar[6])
   py_send("%s+%s 1 " %(mvar[6],mvar[7]))
   py_send("%s+%s+1.0 1 " %(mvar[6],mvar[7]))
   py_send("*new_md_table 1 1 ")
   py_send("*set_md_table_type 1 ")
   py_send("time")
   py_send("*table_name hd_rotation_table ")
   py_send("*table_add")
   py_send("0,0 100.0,100.0 ")	  


def add_motion(tname,val) :
    py_send("*cbody_param_table py %s " %tname)
    py_send("*contact_value py %s " %val)


def add_contact(name,px,py,pz,tname,m_val,rot) :
    py_send("*new_cbody geometry *contact_option geometry_nodes:off ")
    py_send("*contact_body_name %s " %name)
    py_send("*add_contact_body_surfaces %s " %(name+"_s"))
    py_send("*contact_option control:position")
    py_send("*cbody_center_rot %f %f %f " %(px,py,pz))
    py_send("*cbody_param_table py %s " %tname )
    py_send("*contact_value py %s " %m_val )
    py_send("*contact_value prot %f " %rot )
    py_send("*cbody_param_table prot hd_rotation_table " ) 
    py_send("*edit_contact_table ctable1 ")
    py_send("*ctable_entry plate %s " %name)
    py_send("*contact_table_option plate %s contact:on " %name)
    py_send("*prog_string ctable:old_interact friction *ctable_entry_interact plate %s " %name )

def hd_roll_gen(rname,Dia,pitch,pos,r_dia,rot,fr_flag,l_flag,mvar) :
   cx=py_get_float("cbody_par(%s,cx)" %rname)
   cy=py_get_float("cbody_par(%s,cy)" %rname)
   cz=py_get_float("cbody_par(%s,cz)" %rname)   
   print "Referenced Roll Name : ",rname,"Center Position :",cx,cy,cz
   px= cx - pitch 
#  fr_flag = 0 : front
#  fr_flag = 1 : rear 
#  l_flag = 0 : Upper
#  l_flag = 1 : Lower 
   if fr_flag == 0 :
      hdr_name = "front_hd_roll"
   elif fr_flag == 1 :   
      hdr_name = "rear_hd_roll"      
   else :
      print " Error to define the Horizontal location of Hold Down Roll "     
   if l_flag == 0 :
      py= cy - r_dia/2 + Dia/2 + pos
   elif l_flag == 1 :   
      py= cy + r_dia/2 - Dia/2 + pos
   else :
      print " Error to define the vertical location of Hold Down Roll "   
   pz= -1000
   print px,py,pz
   py_send("*select_clear *visible_selected")   
   py_send("*set_curve_type circle_cr *add_curves ")
   py_send("*add_curves ")   
   py_send(" %f %f %f " % (px,py,pz))
   py_send(" %f " % (Dia/2))
   py_send("*expand_reset ") 
   py_send("*set_expand_translation z roll_length+2000 ")
   py_send("*expand_curves all_visible ")
   py_send("*store_surfaces %s all_visible " %(hdr_name+"_s"))
   py_send("*flip_surfaces %s " %(hdr_name+"_s"))
#    add contact   
   if (fr_flag == 0 and l_flag == 0) : 
       m_val = mvar[0]
       tname="urmotion"
   elif (fr_flag == 1 and l_flag == 0) : 
       m_val = mvar[1]
       tname="urmotion"   
   if (fr_flag == 0 and l_flag == 1) : 
       m_val = mvar[4]
       tname="lrmotion"
   elif (fr_flag == 1 and l_flag == 1) : 
       m_val = mvar[5]
       tname="lrmotion"                          
   add_contact(hdr_name,px,py,pz,tname,m_val,rot)
   py_send("*select_clear *invisible_selected ")


   
def create_hd_roll(var,mvar) :
#  Create Table for Motion of Rolls
   rname=""
   hd_loc= var[2]
   hd_loc=hd_loc.strip()
   if hd_loc =="none" :
      print " Hold Down Roll is not created "
   if hd_loc =="upper" :
      print " Hold Down Roll is created at Upper "
      rname="UpperRoll" 
      rnamef = rname+"_1"      
      f_rot = var[10]/(var[3]/2.0)
      l_flag=0
      print "Front Hold Down Roll Rotational Vel. : ",f_rot 
      hd_roll_gen(rnamef,var[3],var[4],var[5],var[9],f_rot,0,l_flag,mvar)
      rnamer = rname+"_"+str(var[0])
      r_rot = var[10]/(var[6]/2.0)      
      print "Rear Hold Down Roll Rotational Vel. : ",r_rot
      hd_roll_gen(rnamer,var[6],-var[7],var[8],var[9],r_rot,1,l_flag,mvar)
   elif hd_loc =="lower" :
      print " Hold Down Roll is created at lower "
      rname="LowerRoll"   
      rnamef = rname+"_1"
      f_rot = -1.0*var[10]/(var[3]/2.0)
      print "Front Hold Down Roll Rotational Vel. : ",f_rot      
      l_flag=1 
      hd_roll_gen(rnamef,var[3],var[4],var[5],var[9],f_rot,0,l_flag,mvar)            
      rnamer = rname+"_"+str(var[1]) 
      r_rot = -1.0*var[10]/(var[6]/2.0)      
      print "Rear Hold Down Roll Rotational Vel. : ",r_rot      
      hd_roll_gen(rnamer,var[6],-var[7],var[8],var[9],r_rot,1,l_flag,mvar)           
   else :
      print " ERROR to define Upper & Lower " 
    
 


   
def main():
   var=get_hd_info() 
   print "/n/n  Start Hold Down Roll Definition /n/n"
   print "Hold Down Rolls variables ",var
   mvar=get_motion_info()
   print "Motion Variables ",mvar
   create_motion_table(mvar)

   create_hd_roll(var,mvar)

   

     
if __name__ == '__main__':
    main()
