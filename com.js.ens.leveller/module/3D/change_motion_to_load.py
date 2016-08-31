from py_mentat import *
from py_post import *

def get_motion_info() :
   mvar=[]
   u_roll_no = py_get_int("u_roll_no") # VAR[0]
   l_roll_no = py_get_int("l_roll_no") # VAR[1]
   URM_ent = py_get_float("URM_ent")   # VAR[2]
   URM_exit = py_get_float("URM_exit") # VAR[3]
   URM_ts = py_get_float("URM_ts")     # VAR[4]
   URM_te = py_get_float("URM_te")     # VAR[5]
   LRM_ent = py_get_float("LRM_ent")   # VAR[6]
   LRM_exit = py_get_float("LRM_exit") # VAR[7] 
   LRM_ts = py_get_float("LRM_ts")     # VAR[8]
   LRM_te = py_get_float("LRM_te")     # VAR[9]
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

def create_nodes(x,y,z,sn) :
   py_send("*add_nodes %f , %f , %f " %(x, y ,z))  # n0 : Center Node of Roll
   py_send("*add_nodes %f , %f , %f " %(x+50.0, y ,z)) # n1 : Aux. Node of Roll 
   cname=sn.split("_")
   if (cname[0]== "UpperRoll" ) : 
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y-25.0 ,z+50.0)) # n2 : 1st node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y-25.0 ,z+50.0)) # n3 : 2nd node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y-25.0 ,z-50.0)) # n4 : 3rd node of spring element      
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y-25.0 ,z-50.0)) # n5 : 4th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y+25.0 ,z+50.0)) # n6 : 5th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y+25.0 ,z+50.0)) # n7 : 6th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y+25.0 ,z-50.0)) # n8 : 7th node of spring element      
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y+25.0 ,z-50.0)) # n9 : 8th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x, y+25.0 ,z))  # n10 : Fixed Point of Roll
   if (cname[0]== "LowerRoll" ) :      
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y+25.0 ,z-50.0)) # n2 : 1st node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y+25.0 ,z-50.0)) # n3 : 2nd node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y+25.0 ,z+50.0)) # n4 : 3rd node of spring element      
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y+25.0 ,z+50.0)) # n5 : 4th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y-25.0 ,z-50.0)) # n6 : 5th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y-25.0 ,z-50.0)) # n7 : 6th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x+50.0, y-25.0 ,z+50.0)) # n8 : 7th node of spring element      
     py_send("*add_nodes %f , %f , %f " %(x-50.0, y-25.0 ,z+50.0)) # n9 : 8th node of spring element
     py_send("*add_nodes %f , %f , %f " %(x, y-25.0 ,z))  # n10 : Fixed Point of Roll

def create_spring(n0,e0,sn,nodes_up,nodes_low,mvar) :
  cname=sn.split("_")
  py_send("*add_elements %i %i %i %i %i %i %i %i " %(n0+2,n0+3,n0+4,n0+5,n0+6,n0+7,n0+8,n0+9))
  py_send("*new_rbe2 *rbe2_name rbe2_"+sn+"_spring ")
  py_send("*rbe2_ret_node %i " %n0)
  py_send("*rbe2_tied_dof 1 *rbe2_tied_dof 2 *rbe2_tied_dof 3 *rbe2_tied_dof 4 *rbe2_tied_dof 5 *rbe2_tied_dof 6 ")
  py_send("*add_rbe2_tied_nodes %i %i %i %i #" %(n0+2,n0+3,n0+4,n0+5))
  py_send("*new_rbe2 *rbe2_name rbe2_"+sn+"_fixed ")
  py_send("*rbe2_ret_node %i " %(n0+10))
  py_send("*rbe2_tied_dof 1 *rbe2_tied_dof 2 *rbe2_tied_dof 3 *rbe2_tied_dof 4 *rbe2_tied_dof 5 *rbe2_tied_dof 6 ")
  py_send("*add_rbe2_tied_nodes %i %i %i %i #" %(n0+6,n0+7,n0+8,n0+9))
  if (cname[0]== "UpperRoll" ) :
      nodes_up.append(n0)
      py_send("*edit_mater upper_dummy_mat ")
      py_send("*add_mater_elements %i #" %e0 )
      py_send("*edit_apply Upper_roll_rot ")
      py_send("*add_apply_nodes %i #" %(n0+1)) 
      val=mvar[2]+(mvar[3]-mvar[2])*((int(cname[1])-1.0)/(mvar[0]-1))
      print "Upper Roll Motion",cname[1],mvar[0],mvar[2],mvar[3],val           
      tname="urmotion_bc"
      create_roll_motion_bcs(sn,tname,val)
      py_send("*add_apply_nodes %d # "%(n0+10))

  if (cname[0]== "LowerRoll" ) :      
      nodes_low.append(n0)
      py_send("*edit_mater lower_dummy_mat ")
      py_send("*add_mater_elements %i #" %e0 )
      py_send("*edit_apply Lower_roll_rot ")
      py_send("*add_apply_nodes %i #" %(n0+1)) 
      val=mvar[6]+(mvar[7]-mvar[6])*((int(cname[1])-1.0)/(mvar[1]-1))
      print "Lower Roll Motion",cname[1],mvar[1],mvar[6],mvar[7],val            
      tname="urmotion_bc"
      create_roll_motion_bcs(sn,tname,val) 
      py_send("*add_apply_nodes %d # "%(n0+10))
  py_send("*edit_apply Fix_spring ")
  py_send("*add_apply_nodes %i # " %(n0+10) )
  py_send("*edit_apply Fix_roll ")
  py_send("*add_apply_nodes %i # " %n0 )
  py_send("*eval "+sn+"_spring_node "+str(n0))
  py_send("*eval "+sn+"_fix_node "+str(n0+10))  
  
def create_post_proc(nodes_up,nodes_low) :
#  u_roll_no=py_get_int("u_roll_no")
#  l_roll_no=py_get_int("l_roll_no")
  fname="post_upper_roll_rforce.proc"
  f=open(fname,'w')
  f.write("*xy_plot_clear \n")
  f.write("*set_history_locations \n")
  for i in range(0,len(nodes_up)) :
    f.write(str(nodes_up[i])+"\n")
  f.write("# \n") 
  f.write("*history_collect 0 100000000 1 \n")  
  f.write("*history_add_var \nTime\nReaction Force Y\n")
  f.write("*get_history_plots *xy_plot_fit \n")
  f.write("*xy_plot_export upper_roll_rforce.txt yes \n")
  f.close() 
  fname="post_upper_roll_dispY.proc"
  f=open(fname,'w')
  f.write("*xy_plot_clear \n")
  f.write("*set_history_locations \n")
  for i in range(0,len(nodes_up)) :
    f.write(str(nodes_up[i])+"\n")
  f.write("# \n") 
  f.write("*history_collect 0 100000000 1 \n")  
  f.write("*history_add_var \nTime\nDisplacement Y\n")
  f.write("*get_history_plots *xy_plot_fit \n")
  f.write("*xy_plot_export upper_roll_dispY.txt yes \n")
  f.close()
  fname="post_lower_roll_rforce.proc"
  f=open(fname,'w')
  f.write("*xy_plot_clear \n")
  f.write("*set_history_locations \n")
  for i in range(0,len(nodes_low)) :
    f.write(str(nodes_low[i])+"\n")
  f.write("# \n") 
  f.write("*history_collect 0 100000000 1 \n")
  f.write("*history_add_var \nTime\nReaction Force Y\n")
  f.write("*get_history_plots *xy_plot_fit \n")
  f.write("*xy_plot_export lower_roll_rforce.txt yes \n")
  f.close() 
  fname="post_lower_roll_dispY.proc"
  f=open(fname,'w')
  f.write("*xy_plot_clear \n")
  f.write("*set_history_locations \n")
  for i in range(0,len(nodes_low)) :
    f.write(str(nodes_low[i])+"\n")
  f.write("# \n") 
  f.write("*history_collect 0 100000000 1 \n")  
  f.write("*history_add_var \nTime\nDisplacement Y\n")
  f.write("*get_history_plots *xy_plot_fit \n")
  f.write("*xy_plot_export lower_roll_dispY.txt yes \n")
  f.close()   
   
  
def change_cbody_control(n0,n2) :
    py_send("*contact_option control:load ")
    py_send("*cbody_control_node %d" %n0)
    py_send("*cbody_control_node_rot %d " %n2)
    print "  Contact body is changed to load Control "

def create_bcs(uv,lv) :
#
# Create Fix Spring BCS (X,Z,RX,RY,RZ): Fix_spring
# Create Fix Roll BCS (X,Z)  Y is supported by Spring : Fix_roll
# Create Rotation Roll BCS (RX,RY,RZ) : Upper_roll_rot & Lower_roll_rot
#
    py_send("*new_apply *apply_type fixed_displacement ")
    py_send("*apply_name Fix_spring ")
    py_send("*apply_dof x *apply_dof_value x ")
    # py_send("*apply_dof y *apply_dof_value y ")
    py_send("*apply_dof z *apply_dof_value z ")
    py_send("*apply_dof rx *apply_dof_value rx ")
    py_send("*apply_dof ry *apply_dof_value ry ")
    py_send("*apply_dof rz *apply_dof_value rz ")	
    py_send("*new_apply *apply_type fixed_displacement ") 
    py_send("*apply_name Fix_roll ")
    py_send("*apply_dof x *apply_dof_value x ")
    py_send("*apply_dof z *apply_dof_value z ")
    py_send("*new_md_table 1 1 " )
    py_send("*table_name rotation_table ")
    py_send("*set_md_table_type 1 time ")
    py_send("*table_add 0 0 1 1 ")
    py_send("*new_apply *apply_type fixed_displacement ")
    py_send("*apply_name Upper_roll_rot ")
    py_send("*apply_dof x *apply_dof_value x ")
    py_send("*apply_dof y *apply_dof_value y ")
    py_send("*apply_dof z *apply_dof_value z %f" %uv)
    py_send("*apply_dof_table z rotation_table ")
    py_send("*new_apply *apply_type fixed_displacement ")
    py_send("*apply_name Lower_roll_rot ")
    py_send("*apply_dof x *apply_dof_value x ")
    py_send("*apply_dof y *apply_dof_value y ")
    py_send("*apply_dof z *apply_dof_value z %f" %lv)
    py_send("*apply_dof_table z rotation_table ")

def create_roll_motion_bcs(sn,tname,val) :
    py_send("*new_apply *apply_type fixed_displacement ")
    py_send("*apply_name Fix_spring_%s " %sn)
    py_send("*apply_dof_table y %s" %tname)
    py_send("*apply_dof y *apply_dof_value y %f" %val)    
    py_send("*edit_loadcase levelling ")
    py_send("*add_loadcase_loads Fix_spring_%s " %sn)
          

def change_option() :
#    py_send("*identify_applys *regen ")
    py_send("*edit_loadcase levelling ")
    py_send("*add_loadcase_loads Fix_spring ")
    py_send("*add_loadcase_loads Fix_roll ")
    py_send("*add_loadcase_loads Upper_roll_rot ")
    py_send("*add_loadcase_loads Lower_roll_rot ")
#    py_send("*loadcase_option converge:resid_or_disp ") 
    py_send("*edit_job job1 ")
    py_send("*add_job_applys Fix_spring ")
    py_send("*add_job_applys Fix_roll ")
    py_send("*add_job_applys Upper_roll_rot ")
    py_send("*add_job_applys Lower_roll_rot ")
    py_send("*job_option separation:stress ")
    py_send("*job_param rel_sepstress 0.1 ")
#    py_send("*save_model ")

def create_dummy_mats() :
    mill_k=py_get_float("u_m_stiffness")/100.0  
    # mill stiffnes scale factor should be changed due to incorrect calculating section of spring element.
    # must be scaled down to 10000.0
    py_send("*edit_mater material1 *copy_mater ")
    py_send("*mater_name upper_dummy_mat  ")
    py_send("*mater_param general:mass_density 7.85e-009*100  ")
    py_send("*mater_param structural:youngs_modulus "+str(mill_k))
    py_send("*clear_mater_param_table structural:youngs_modulus")
    py_send("*mater_option structural:plasticity:off ")
    py_send("*mater_option structural:thermal_expansion:off ")
    mill_k=py_get_float("l_m_stiffness")/100.0
    # mill stiffnes scale factor is changed due to incorrect calculating section of spring element.    
    py_send("*edit_mater material1 *copy_mater ")
    py_send("*mater_name lower_dummy_mat  ")
    py_send("*mater_param general:mass_density 7.85e-009*100  ")
    py_send("*mater_param structural:youngs_modulus "+str(mill_k))
    py_send("*clear_mater_param_table structural:youngs_modulus")    
    py_send("*mater_option structural:plasticity:off ")
    py_send("*mater_option structural:thermal_expansion:off ")

def add_tail_dummy() :
    py_send("*select_method_box ")
    py_send("*select_faces ")
    py_send("-L-0.1*T/element_num_of_thickness_direction -L+0.1*T/element_num_of_thickness_direction ")
    py_send("-100000 100000 ")
    py_send("-100000 100000 ")
    py_send("@set($convert_entities,faces) ") 
    py_send("*face_quads all_selected ")
    py_send("*expand_reset ")
    py_send("*set_expand_translation x 0-L/10 ")
    py_send("*select_clear *select_elements_class quad4 *visible_selected  ")
    py_send("*expand_elements all_visible ")
    py_send("*sweep_nodes all_existing ")
    py_send("*edit_mater material1 *copy_mater ")
    py_send("*mater_name dummy_mat  ")
    py_send("*mater_param general:mass_density 7.85e-009*100  ")
    py_send("*mater_param structural:youngs_modulus 1/100  ")
    py_send("*mater_option structural:plasticity:off ")
    py_send("*mater_option structural:thermal_expansion:off ")
    py_send("*add_mater_elements all_visible ")
    py_send("*select_clear *invisible_selected  ")


def create_rmotion_bc_table(var) :
   py_send("*new_md_table 1 1 ")
   py_send("*table_name urmotion_bc ")
   py_send("*set_md_table_type 1 time ")
   py_send("*table_add ")
   py_send("0  0 ")
   py_send("%s 0 " %var[4])
   py_send("%s+%s 1 " %(var[4],var[5]))
   py_send("%s+%s+1.0 1 " %(var[4],var[5]))
   py_send("*new_md_table 1 1 ")
   py_send("*table_name lrmotion_bc ")
   py_send("*set_md_table_type 1 time ")
   py_send("*table_add ")
   py_send("0  0 ")
   py_send("%s 0 " %var[8])
   py_send("%s+%s 1 " %(var[8],var[9]))
   py_send("%s+%s+1.0 1 " %(var[8],var[9]))    
    
def main():
  mvar=get_motion_info()
  nodes_up=[]
  nodes_low=[]
  py_send("*set_undo off  *set_update off ")
  py_send("*renumber_nodes")
  py_send("*renumber_elements")
  nnode=py_get_int("nnodes()")
  nbody=py_get_int("ncbodys()")
  nelem=py_get_int("nelements()")
  uname=py_get_string("cbody_name_index(%d)" %2)
  lname=py_get_string("cbody_name_index(%d)" %(nbody-1))
  upper_v = py_get_float("cbody_par(%s,vrot)" % uname)
  lower_v = py_get_float("cbody_par(%s,vrot)" % lname)  
  # print upper_v, lower_v
  create_dummy_mats()
  create_bcs(upper_v,lower_v) # Spring Support Roll Rotation & Roll BCs   
  create_rmotion_bc_table(mvar)   
  py_send("*set_element_class hex8 ")  
  for i in range(2,nbody):
    sn = py_get_string("cbody_name_index(%d)" % i)
    id = py_get_int("cbody_id(%s)" % sn)
    cx = py_get_float("cbody_par(%s,cx)" % sn)
    cy = py_get_float("cbody_par(%s,cy)" % sn)
    cz = py_get_float("cbody_par(%s,cz)" % sn)
  #  print " Width of Plate ", py_get_float("W")
    cz = cz+py_get_float("W")/2.0+1000.0
    py_send("*edit_contact_body %s " % sn)
    create_nodes(cx,cy,cz,sn)
    n0,e0=nnode+(i-2)*11+1,nelem+(i-2)+1
    create_spring(n0,e0,sn,nodes_up,nodes_low,mvar)
    change_cbody_control(n0,n0+1)
    print " Contact Body ", i, " Id ",id, " Name ", sn  
    print " Contact Body ",i," Center :",cx,cy,cz
#    print nodes_up, nodes_low

  change_option()
  create_post_proc(nodes_up,nodes_low)
  # add_tail_dummy()    
  py_send("*define m_control load")
  py_send("*set_undo on  *set_update on ")   

if __name__ == '__main__':
    py_connect('',40007)
    main()
    py_disconnect()
