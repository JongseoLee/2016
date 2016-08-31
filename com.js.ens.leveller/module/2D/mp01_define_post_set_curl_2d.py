from py_mentat import *
# from py_post import *


def save_set(setname,x,y,z) : 
   py_send("*select_clear")
   py_send("*select_method_point_dist")
   tol=py_get_float("T")/2
   print "Tolerance of finding nearest point ", tol
   py_send("*set_select_distance "+str(tol)) 
   biased_x=tol/15 # it is for avoiding no search when half location
   py_send("*select_nodes " + str(x+biased_x)+","+str(y)+","+str(z))
   py_send("*store_nodes "+setname + " all_selected")
   py_send("*set_select_distance 0.001") 

def intersect_curves() :
	ql=py_get_float("L")/4.0
	fcl=py_get_float("fcl")
	rcl=py_get_float("rcl")
	print ql,fcl,rcl
	py_send("*sweep_tolerance 0.1 ")	
	if ( ql >= fcl and ql >= rcl) : 
	  py_send("*intersect_curves")
	  py_send(" 7 3 # ")
	  py_send(" 8 4 # ")
	  py_send(" 9 15 # ")
	  py_send(" 10 21 # ")
	  py_send(" 11 6 # ")
	  py_send("*sweep_all ")
	  ls_pt,le_pt,h_pt=[3],[12],[3,35,43,51,12]	 
	if ( ql >= fcl and ql < rcl) : 
	  py_send("*intersect_curves")
	  py_send(" 7 3 # ")
	  py_send(" 8 4 # ")
	  py_send(" 9 15 # ")
	  py_send(" 10 6 # ")
	  py_send(" 11 23 # ")
	  py_send("*sweep_all ")
	  ls_pt,le_pt,h_pt=[3],[12],[3,35,43,52,12]
	if ( ql < fcl and ql >= rcl) : 
	  py_send("*intersect_curves")
	  py_send(" 7 3 # ")
	  py_send(" 8 3 # ")
	  py_send(" 9 4 # ")
	  py_send(" 10 19 # ")
	  py_send(" 11 6 # ")	    
	  py_send("*sweep_all ")
	  ls_pt,le_pt,h_pt=[3],[12],[3,36,45,53,12]
	if ( ql < fcl and ql < rcl) : 
	  py_send("*intersect_curves")
	  py_send(" 7 3 # ")
	  py_send(" 8 3 # ")
	  py_send(" 9 4 # ")
	  py_send(" 10 6 # ")
	  py_send(" 11 23 # ")	    	  
	  py_send("*sweep_all ")
	  ls_pt,le_pt,h_pt=[3],[12],[3,36,45,54,12]
	return ls_pt,le_pt,h_pt 


def create_path_sets(fname) :
   lsset=["ALS1"] # Longi Path Start Point
   leset=["ALE1"] # Longi Path End Point
   hset=["HF","HQ1","HC","HQ3","HR"]
   ls_pt,le_pt,h_pt = intersect_curves()
   longi_no=len(lsset)
   py_send("*define longi_path_no "+str(longi_no))
   ls_flag=0
   le_flag=0
   h_flag=0
   
   # Longi Start Point
   x=py_get_float("point_x(%d)" %ls_pt[0]) 
   y=py_get_float("point_y(%d)" %ls_pt[0]) 
   z=py_get_float("point_z(%d)" %ls_pt[0]) 
   #ls_index=find_index("ALS1",ls_pt[0])         
   save_set("ALS1",x,y,z)
   print "Path Point Coordinate ",ls_pt[0],"ALS1", x,y,z                        
   ls_flag=ls_flag+1
   # Longi End Point
   x=py_get_float("point_x(%d)" %le_pt[0]) 
   y=py_get_float("point_y(%d)" %le_pt[0]) 
   z=py_get_float("point_z(%d)" %le_pt[0]) 
   #le_index=find_index("ALE1",le_pt[0]) 
   save_set("ALE1",x,y,z)
   print "Path Point Coordinate ",le_pt[0],"ALE1", x,y,z                
   le_flag=le_flag+1
   for i in range(0,len(h_pt)) :
     print " Getting Hisotry Position with Point No. = ",h_pt[i] 
     x=py_get_float("point_x(%d)" %h_pt[i])
     y=py_get_float("point_y(%d)" %h_pt[i])  
     z=py_get_float("point_z(%d)" %h_pt[i]) 
     #h_index=find_index(h_pt[i],i) 
     save_set(hset[i],x,y,z)
     print "History Point Coordinate ", i,hset[i], x,y,z        
     h_flag=h_flag+1

def main():
   fname=py_get_string("model_name()")
   py_send("*set_undo off")  
   create_path_sets(fname)
   py_send("*set_undo on")   

     
if __name__ == '__main__':
    main()
