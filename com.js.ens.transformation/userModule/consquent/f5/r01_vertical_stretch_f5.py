#
# This Routine is for stretching vertical Nodes of Plate Model File
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
   
def stretch_nodes(rn) :
   nelems = py_get_int("nelements()")    
   tpath,bpath=[],[]
#--------------------------------------------
# Get Information
#--------------------------------------------
   tdiv=py_get_int("t_div")  
   wdiv=py_get_int("w_div")  
   ldiv=py_get_int("l_div")
   print tdiv,wdiv,ldiv
#--------------------------------------------
# Stretch Front Nodes
#--------------------------------------------
   ntstart=py_get_int(rn[0])
   nbstart=py_get_int(rn[2])
   print ntstart,nbstart
   tpath.append(ntstart),bpath.append(nbstart)
   py_send("*stretch_nodes ")
   py_send(str(ntstart)+"  "+str(nbstart)+"  #")
   for i in range(1,wdiv) :
     for e_id in range(1,nelems+1) :
       bot=py_get_int("element_node_id(%d,3)" %e_id)
       top=py_get_int("element_node_id(%d,7)" %e_id)
       if top == ntstart :
         tel=e_id 
         nxt_tnode=py_get_int("element_node_id(%d,6)" %e_id)      
         tpath.append(nxt_tnode)
       if bot == nbstart :
         bel=e_id     
         nxt_bnode=py_get_int("element_node_id(%d,2)" %e_id)      
         bpath.append(nxt_bnode)
     print "Element & Node ID ===",tel,nxt_tnode,bel,nxt_bnode
     py_send(str(nxt_tnode)+"  "+str(nxt_bnode)+"  #")
     ntstart=nxt_tnode
     nbstart=nxt_bnode    
#--------------------------------------------
# Stretch Rear Nodes
#--------------------------------------------
   ntstart=py_get_int(rn[6])
   nbstart=py_get_int(rn[8])
   print ntstart,nbstart
   tpath.append(ntstart),bpath.append(nbstart)
   py_send("*stretch_nodes ")
   py_send(str(ntstart)+"  "+str(nbstart)+"  #")
   for i in range(1,wdiv) :
     for e_id in range(1,nelems+1) :
       bot=py_get_int("element_node_id(%d,4)" %e_id)
       top=py_get_int("element_node_id(%d,8)" %e_id)
       if top == ntstart :
         tel=e_id 
         nxt_tnode=py_get_int("element_node_id(%d,5)" %e_id)      
         tpath.append(nxt_tnode)
       if bot == nbstart :
         bel=e_id     
         nxt_bnode=py_get_int("element_node_id(%d,1)" %e_id)      
         bpath.append(nxt_bnode)
     print "Element & Node ID ===",tel,nxt_tnode,bel,nxt_bnode
     py_send(str(nxt_tnode)+"  "+str(nxt_bnode)+"  #")
     ntstart=nxt_tnode
     nbstart=nxt_bnode    

#--------------------------------------------
# Stretch Center Nodes
#--------------------------------------------
   ntstart=py_get_int(rn[0])
   nbstart=py_get_int(rn[2])
   print ntstart,nbstart
   tpath.append(ntstart),bpath.append(nbstart)
   py_send("*stretch_nodes ")
   py_send(str(ntstart)+"  "+str(nbstart)+"  #")
   for i in range(1,ldiv) :
     for e_id in range(1,nelems+1) :
       bot=py_get_int("element_node_id(%d,3)" %e_id)
       top=py_get_int("element_node_id(%d,7)" %e_id)
       if top == ntstart :
         tel=e_id 
         nxt_tnode=py_get_int("element_node_id(%d,8)" %e_id)      
         tpath.append(nxt_tnode)
       if bot == nbstart :
         bel=e_id     
         nxt_bnode=py_get_int("element_node_id(%d,4)" %e_id)      
         bpath.append(nxt_bnode)
     print "Element & Node ID ===",tel,nxt_tnode,bel,nxt_bnode
     py_send(str(nxt_tnode)+"  "+str(nxt_bnode)+"  #")
     ntstart=nxt_tnode
     nbstart=nxt_bnode    
   return    

def get_thickness(p,pvar,rn,ntstart,nbstart,fr_flag) :

   nelems = py_get_int("nelements()")    
   tpath,bpath=[],[]	 
   wdiv=int(pvar[5])	 
   
   tpath.append(ntstart),bpath.append(nbstart)
   ninc = p.increments()
   print "no. of increment",ninc
   p.moveto(ninc-1)
   nelems=p.elements()
   k=0
   for i in range(0,wdiv) :
     #print " Node No. of Path ",ntstart,nbstart
     k=0
     while k < nelems:
       el = p.element(k)
       if fr_flag == 0 : # Front Path
       	 top=el.items[6]
         bot=el.items[2]
       if fr_flag == 1 : # Rear Path
       	 top=el.items[7]
         bot=el.items[3]         
       #print top,bot,el.len,el
       if top == ntstart and fr_flag == 0  :
         nxt_tnode=el.items[5]
         tpath.append(nxt_tnode)
       if bot == nbstart and fr_flag == 0   :
         nxt_bnode=el.items[1]
         bpath.append(nxt_bnode)
       if top == ntstart and fr_flag == 1   :
         nxt_tnode=el.items[4]
         tpath.append(nxt_tnode)
       if bot == nbstart and fr_flag == 1  :
         nxt_bnode=el.items[0]
         bpath.append(nxt_bnode)
       k=k+1     
     print "Top & Bottom Node ID ===",nxt_tnode,nxt_bnode
     ntstart=nxt_tnode
     nbstart=nxt_bnode 
   tnx,tny,tnz=[],[],[]
   tndx,tndy,tndz=[],[],[]
   bnx,bny,bnz=[],[],[]
   bndx,bndy,bndz=[],[],[]   
   n = p.nodes()
   itpath,ibpath=[],[]
   print tpath,bpath
   for i in range(0,n):
     nod = p.node(i)
     #print nod
     for j in range(0,len(tpath)) :
       if nod.id == tpath[j] :
     		 itpath.append(i)
     		 tnx.append(nod.x)
     		 tny.append(nod.y)
     		 tnz.append(nod.z)
     		 dx,dy,dz=p.node_displacement(i)
     		 # print nod.id,p.node_displacement(i)
     		 tndx.append(dx)
     		 tndy.append(dy)
     		 tndz.append(dz)
     		 # print nod.id, tpath[j],nod.x 
       if nod.id == bpath[j] :
         ibpath.append(i)
         bnx.append(nod.x)
         bny.append(nod.y)         
         bnz.append(nod.z)         
         dx,dy,dz=p.node_displacement(i)
         # print nod.id, p.node_displacement(i)
         bndx.append(dx)
         bndy.append(dy)
         bndz.append(dz)
         #print nod.id, bpath[j],nod.x 
   # print itpath, tndy, ibpath,bndy  
     	 #print "id", id
     	 #print "element id",j,el,el.len
   print "Length of  ",len(tny),len(tndx)
   # wf=open(oname,"w")
   # s=" Distance from Center, top profile, bottom profile, thk profile  \n"
   # wf.writelines(s)
   thk_profile=[]
   for i in range(0,len(tny)) :
   	  tp=tny[i]+tndy[i]
   	  bp=bny[i]+bndy[i]
#   	  print p.node(itpath[i])
   	  thk_profile.append(tp-bp)
   thk_avr=sum(thk_profile) / float(len(thk_profile))	
   print "Average Thickness of plate "  , thk_avr 

   return thk_avr           
            
def main():   
   rnname=["FRCT","FRCM","FRCB","FRET","FREM","FREB","RRCT","RRCM","RRCB","RRET","RREM","RREB"]      
   pvar=get_plate_info()   
# rname : Vortex Name in Parameters   
#   fname = get_fname()
   stretch_nodes(rnname)
   py_send("*relax_surface_fixed *relax_nodes  all_visible "  )
   py_send("*relax_surface_fixed *relax_nodes  all_visible "  )
   py_send("*relax_surface_fixed *relax_nodes  all_visible "  )  

# Get Thickness Information
   p = post_open('dummy_f5_job1.t16')   # Code must be updated to define automatically postfile name later 
   ntstart=py_get_int(rnname[0])
   nbstart=py_get_int(rnname[2]) 
   thk_avr=get_thickness(p,pvar,rnname,ntstart,nbstart,0) 
   py_send("*define dp_thk_f %f " %thk_avr )   
   ntstart=py_get_int(rnname[6])
   nbstart=py_get_int(rnname[8]) 
   thk_avr=get_thickness(p,pvar,rnname,ntstart,nbstart,1) 
   py_send("*define dp_thk_r %f " %thk_avr )      
      
   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
      
    py_disconnect()






