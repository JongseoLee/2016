#
#
from py_mentat import *
from py_post import *
# from sets import Set


def get_fname():  # get file name
   fname = py_get_string("model_name()")
   print "File names ==",fname
   return fname
   
def claen_model() :
   py_send("*select_clear")
   py_send("*select_contact_body 01_Plate")
   py_send("*remove_elements")
   py_send("all_unselected")
   py_send("*remove_unused_nodes *clear_geometry")
   py_send("*grid_fix_v")
   py_send("*grid_u_domain -500 500")
   py_send("*grid_u_spacing 100")
   py_send("*grid_w_domain -1500 1500")
   py_send("*grid_w_spacing 100")
   py_send("*set_grid on *grid_style_lines")
   py_send("*show_view 2 *fill_view") 
   py_send("*select_clear  *renumber_all")
   return 

def find_max_min_x() :
   nunodes = py_get_int("nnodes()")  
   print "Number of Nodes    ==",nunodes
   nelems = py_get_int("nelements()")
   print "Number of Elements ==",nelems
   max_x,min_x=-1000000.0,1000000.0
   max_y,min_y=-1000000.0,1000000.0
   max_z,min_z=-1000000.0,1000000.0      
   i=1
   while i <= nunodes :
     n_id= py_get_int("node_id(%i)"%i)
     c_x = py_get_float("node_x(%i)"%n_id)
     c_y = py_get_float("node_y(%i)"%n_id)
     c_z = py_get_float("node_z(%i)"%n_id)
#     print n_id," :  ",c_x,c_y,c_z
     if (c_x > max_x) : 
        max_x = c_x  
     if (c_x < min_x) : 
        min_x = c_x  
     if (c_y > max_y) : 
        max_y = c_y  
     if (c_y < min_y) : 
        min_y = c_y  
     if (c_z > max_z) : 
        max_z = c_z  
     if (c_z < min_z) : 
        min_z = c_z  
     i=i+1
   print " Max. X :  ",max_x," Min. X :  ",min_x     
   print " Max. Y :  ",max_y," Min. Y :  ",min_y
   print " Max. Z :  ",max_z," Min. Z :  ",min_z
   cx,cy,cz=str((max_x+min_x)/2),str((max_y+min_y)/2),str((max_z+min_z)/2)
   py_send("*origin "+cx+" "+cy+" "+cz)
   return max_x,min_x,max_y,min_y,max_z,min_z  

def find_corner_node(max_x,min_x,max_y,min_y,max_z,min_z) :
   nunodes = py_get_int("nnodes()")  
   print "Number of Nodes    ==",nunodes
   nelems = py_get_int("nelements()")
   print "Number of Elements ==",nelems
   d1,d2,d3,d4,d5,d6,d7,d8 = 1.0e20,1.0e20,1.0e20,1.0e20,1.0e20,1.0e20,1.0e20,1.0e20
   i=1
   while i <= nunodes :
     n_id= py_get_int("node_id(%d)"%i)
     c_x = py_get_float("node_x(%d)"%n_id)
     c_y = py_get_float("node_y(%d)"%n_id)
     c_z = py_get_float("node_z(%d)"%n_id)
     d=((min_x-c_x)**2+(min_y-c_y)**2+(max_z-c_z)**2)**0.5
     if (d < d1) : 
        n1 = n_id
        d1 = d 
     i=i+1
#   print " Corner Node 1 ",n1,d1
   return 

def find_node_set_id(name):
  n = py_get_int("nsets()")
  for i in range(1,n+1):
    id = py_get_int("set_id(%d)" % i)
    sname = py_get_string("set_name(%d)" %id)
    if( sname == name):
          return id 
  return -1
  
def get_node_id() :
    n_surf=[]
    name="surf_nodes"
    id=find_node_set_id(name)
    if  id >= 0 :
#     print name[n],id
      node_no=py_get_int("nset_entries(%d)" %id)
      for i in range(1,node_no+1) :  
        node_id=py_get_int("set_entry(%d,%d)" %(id,i))
        n_surf.append(node_id)
#        print "set no",id,node_no,i,node_id
    return n_surf
    
def find_surface_nodes() :
   py_send("*store_nodes surf_nodes all_surface")     
   n_surf=get_node_id()
   return n_surf

def select_nodes(nodes) :
   py_send("*select_clear *select_nodes ")
   for i in range (0, len(nodes)) :
     py_send("%d "%nodes[i])                 
   py_send(" # \n")  

def remove_duplicate(l0) :
    ln=[]
    for i in l0:
       if i not in ln:
          ln.append(i)
    ln.sort(), l0.sort()
#    print len(l0),"\n",l0,len(ln),"\n",ln 
    return ln

def find_single(l1,l2) :
    ls=[]
    for i in l1:
       if i not in l2:
          ls.append(i)
#    print len(l1),"\n",l1,len(l2),"\n",l2
#    print " Single Elements \n",ls,len(ls) 
    return ls

def find_ver_path(n) :
   nelems = py_get_int("nelements()")    
#--------------------------------------------
   n1,n2,n3,n4,n5,n6,n7,n8=[],[],[],[],[],[],[],[]
   for e_id in range(1,nelems+1) :
      print "Element ID ===",e_id
      n1.append(py_get_int("element_node_id(%d,1)" %e_id))
      n2.append(py_get_int("element_node_id(%d,2)" %e_id))
      n3.append(py_get_int("element_node_id(%d,3)" %e_id))
      n4.append(py_get_int("element_node_id(%d,4)" %e_id))
      n5.append(py_get_int("element_node_id(%d,5)" %e_id))
      n6.append(py_get_int("element_node_id(%d,6)" %e_id))
      n7.append(py_get_int("element_node_id(%d,7)" %e_id))
      n8.append(py_get_int("element_node_id(%d,8)" %e_id))
#   print "NNNN==",n4,n8   
#--------------------------------------------   
   for i in range(0, len(n)) :
       npath=[]
#   get 4th & 8th node no. of e_id
       nc=n[i]
       print " Current Node ID ",nc
       npath.append(nc)
#---------------------------------------
       if nc in n1 :   
         n1_id= n1.index(nc)
         print "n1_ID for node ",nc,n1_id
         k=1
         while k <= 3 :
           nc=n5[n1_id]
           print "nc ====",nc
           npath.append(nc)
           n1_id= n1.index(nc)
           print "n1 ID ==",nc,n1_id 
           k=k+1 
         npath.append(n5[n1_id])
         print n[i],npath
#---------------------------------------
       elif nc in n2 :   
         n2_id= n2.index(nc)
         print "n2_ID for node ",nc,n2_id
         k=1
         while k <= 3 :
           nc=n6[n2_id]
           print "nc ====",nc
           npath.append(nc)
           n2_id= n2.index(nc)
           print "n2 ID ==",nc,n2_id 
           k=k+1 
         npath.append(n6[n2_id])
         print n[i],npath       
#---------------------------------------
       elif nc in n3 :   
         n3_id= n3.index(nc)
         print "n3_ID for node ",nc,n3_id
         k=1
         while k <= 3 :
           nc=n7[n3_id]
           print "nc ====",nc
           npath.append(nc)
           n3_id= n3.index(nc)
           print "n3 ID ==",nc,n3_id 
           k=k+1 
         npath.append(n7[n3_id])
         print n[i],npath
#---------------------------------------
       elif nc in n4 :   
         n4_id= n4.index(nc)
         print "N4_ID for node ",nc,n4_id
         k=1
         while k <= 3 :
           nc=n8[n4_id]
           print "nc ====",nc
           npath.append(nc)
           n4_id= n4.index(nc)
           print "N4 ID ==",nc,n4_id 
           k=k+1 
         npath.append(n8[n4_id])
         print n[i],npath
#---------------------------------------
       py_send("*stretch_nodes ") 
       for ia in range (0,len(npath)) :
          py_send(str(npath[ia]))
       py_send("#  \n")   

   return    
        
         
def nface(n_surf) :
     nelems = py_get_int("nelements()")
     nface1,nface2,nface3,nface4,nface5,nface6,nface7,nface8=[],[],[],[],[],[],[],[]
     for e_id in range(1,nelems+1) :
#     for e_id in range(1,10+1) :
       n1=py_get_int("element_node_id(%d,1)" %e_id)
       n2=py_get_int("element_node_id(%d,2)" %e_id)
       n3=py_get_int("element_node_id(%d,3)" %e_id)
       n4=py_get_int("element_node_id(%d,4)" %e_id)
       n5=py_get_int("element_node_id(%d,5)" %e_id)
       n6=py_get_int("element_node_id(%d,6)" %e_id)
       n7=py_get_int("element_node_id(%d,7)" %e_id)
       n8=py_get_int("element_node_id(%d,8)" %e_id)
#       print n1,n2,n3,n4,n5,n6,n7,n8
       for ns_id in range(0,len(n_surf)) :
           no=n_surf[ns_id]
           if (n1==no) : 
               nface1.append(no)
               nface4.append(no)
               nface5.append(no)                              
           if (n2==no) : 
               nface1.append(no)
               nface2.append(no)
               nface5.append(no)
           if (n3==no) : 
               nface2.append(no)
               nface3.append(no)
               nface5.append(no)
           if (n4==no) : 
               nface3.append(no)
               nface4.append(no)
               nface5.append(no)
           if (n5==no) : 
               nface1.append(no)
               nface4.append(no)
               nface6.append(no)
           if (n6==no) : 
               nface1.append(no)
               nface2.append(no)
               nface6.append(no)          
           if (n7==no) : 
               nface2.append(no)
               nface3.append(no)
               nface6.append(no)
           if (n8==no) : 
               nface3.append(no)
               nface4.append(no)
               nface6.append(no)
     nface5.sort()
#     print nface5
     nface5=remove_duplicate(nface5)
     top=find_single(nface5,nface6)
     print " Top Nodes ",top
     select_nodes(top)
     find_ver_path(top)     
     return
  
def remove_ext_elem() :
   py_send("*select_clear")
   py_send("*select_method_user_box")   
#   py_send("*pick_inside_partial ")   
   py_send("*select_elements -500 500 -1001 1001 -10000 10000")      
   py_send("*remove_elements")
   py_send("all_unselected")
   py_send("*remove_unused_nodes ")
   py_send("*show_view 2 *fill_view") 
   py_send("*select_clear  *renumber_all")
   py_send("*select_method_single")
   py_send("*select_clear")      

               
def main():   
   py_send("*restore_model")     
   fname = get_fname()
   claen_model()
   max_x,min_x,max_y,min_y,max_z,min_z=find_max_min_x()
   remove_ext_elem()
   max_x,min_x,max_y,min_y,max_z,min_z=find_max_min_x()
#   find_corner_node(max_x,min_x,max_y,min_y,max_z,min_z)
   n_surf=find_surface_nodes()
#   print n_surf,len(n_surf)
   nface(n_surf)
   
   py_send("*system_reset")     
   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
  
    py_disconnect()






