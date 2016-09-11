#
# This Routine is for vertical down scaling vertical position at the dummy plate boundary
# It will be used in remeshing step
#
from py_mentat import *
from py_post import *
# from sets import Set


def get_fname():  # get file name
   fname = py_get_string("model_name()")
   print "File names ==",fname
   return fname

def get_plate_info() :
#  
#  pvar[0] : p_in      : initial position of plate : mm :                   
#  pvar[1] : p_thk     : thickness of plate : mm : (ENTRY THK) 
#  pvar[2] : p_wid     : width of plate : mm : (STP WID)                    
#  pvar[3] : p_len     : length of plate : mm : (STP LEN)                   
#  pvar[4] : t_div     : meshing division no. in plate thickness direction
#  pvar[5] : w_div     : meshing division no. in plate width direction
#  pvar[6] : l_div     : meshing division no. in plate long. direction
#  pvar[7] : pass_line : Pass line : mm  : (PAS LINE)
#  pvar[8] : roll_gap  : roll gap : mm : (ROL GAP)
#  pvar[9] : roller table y coordinate
   pvar=[]
   pvar.append(py_get_float("p_in")) 
   pvar.append(py_get_float("p_thk"))
   pvar.append(py_get_float("p_wid"))
   pvar.append(py_get_float("p_len"))
   pvar.append(py_get_int("t_div"))   
   pvar.append(py_get_int("w_div"))      
   pvar.append(py_get_int("l_div"))         
   pvar.append(py_get_float("pass_line")) 
   pvar.append(py_get_float("roll_gap"))               
   pvar.append(py_get_float("pass_line_adj")-py_get_float("p_thk")/2.0)            
   
   print "plate variables ",pvar
   
   return pvar    

def get_center_path(rn,pvar) :
   nelems = py_get_int("nelements()")    
   fcpath,rcpath=[],[]
#--------------------------------------------
# Get Information
#--------------------------------------------
   tdiv=int(pvar[4])  
   wdiv=int(pvar[5])
   ldiv=int(pvar[6])
   print tdiv,wdiv,ldiv
#--------------------------------------------
# scale down Front & Rear mid. Nodes
#--------------------------------------------
   fcstart=py_get_int(rn[1])
   rcstart=py_get_int(rn[7])
   print fcstart,rcstart
   fcpath.append(fcstart),rcpath.append(rcstart)
   for i in range(1,wdiv+1) :
     for e_id in range(1,nelems+1) :
       fcen=py_get_int("element_node_id(%d,3)" %e_id)
       rcen=py_get_int("element_node_id(%d,4)" %e_id)
       if fcen == fcstart :
         fel=e_id 
         nxt_fnode=py_get_int("element_node_id(%d,2)" %e_id)      
         fcpath.append(nxt_fnode)
       if rcen == rcstart :
         rel=e_id     
         nxt_rnode=py_get_int("element_node_id(%d,1)" %e_id)      
         rcpath.append(nxt_rnode)
     # print "Element & Node ID for front & Rear Mid. Path ===",fel,nxt_fnode,rel,nxt_rnode
     fcstart=nxt_fnode
     rcstart=nxt_rnode    
   return  fcpath,rcpath  

def select_nodes(cx,cy,cz,tol) :
	  py_send("*select_clear ")
	  py_send("*select_method_box ")
	  py_send("*select_nodes  ")
	  py_send(str(cx-tol) + "   " + str(cx+tol))
	  py_send(str(cy-tol*1.0e10) + "   " + str(cy+tol*1.0e10))
	  py_send(str(cz-tol) + "   " + str(cz+tol))
	  py_send("*select_method_single ")




def move_nodes(path,tol,pvar) :
   py_send("*move_reset")
   py_send("*set_move_scale_factors 1 0.7 0.0 ")
   for i in range(0,len(path)):
     cx=py_get_float("node_x(%d)" %path[i])  
     cy=py_get_float("node_y(%d)" %path[i])  
     cz=py_get_float("node_z(%d)" %path[i])       
     print "Center node No. ",path[i],cx,cy,cz
     mx0,my0,mz0 = cx,cy,cz
     if i == (len(path)-1) : 
     	  mz0 = cz - pvar[2]/pvar[5]/4.0
     select_nodes(cx,cy,cz,tol)
     py_send("*set_move_point ")     
     py_send("%f %f %f " %(mx0,my0,mz0))
     py_send("*move_nodes all_selected")
   py_send("*select_clear ")
   py_send("*move_reset")
   return
   
def scle_down(pvar,fpath,rpath) :
   tol_d=pvar[1]
   # tol=min(pvar[1]/pvar[4]/tol_d,pvar[2]/2.0/pvar[5]/tol_d,pvar[3]/pvar[6]/tol_d)
   #       p_thk/t_div/tol_d      p_wid/2.0/w_div/tol_d    p_len/l_div/tol_d     
   tol=min(pvar[1]/pvar[4],pvar[2]/2.0/pvar[5],pvar[3]/pvar[6])
   
   print "Selection Tolerance =",tol
   move_nodes(fpath,tol,pvar) 
   move_nodes(rpath,tol,pvar) 
   return



            
def main():   
   rnname=["FRCT","FRCM","FRCB","FRET","FREM","FREB","RRCT","RRCM","RRCB","RRET","RREM","RREB"]      
#   fname = get_fname()
   pvar=get_plate_info()
   fcpath,rcpath=get_center_path(rnname,pvar)
   print "Front Center Nodes ",fcpath
   print "Rear Center Nodes ",rcpath
   scle_down(pvar,fcpath,rcpath)


   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
      
    py_disconnect()






