from py_mentat import *



def create_surface() :
#
# Start of Create Post Sections & Points sets
#
#
        py_send("*remove_curves")
        py_send("all_existing")
        py_send("*set_surface_type quad")
        py_send("*add_surfaces")
        py_send("point(0+L/100,-T*50,0)")
        py_send("point(0+L/100,T*50,0)")
        py_send("point(-L-L/100,T*50,0)")
        py_send("point(-L-L/100,-T*50,0)")
        py_send("*duplicate_reset")
        py_send("*set_duplicate_repetitions 2")
        py_send("*duplicate_surfaces 7 #")
        py_send("*set_duplicate_translation z W/4")
        py_send("*set_duplicate_repetitions 4 ")
        py_send("*duplicate_surfaces  7 to 9  # ")
        py_send("*add_surfaces")
        py_send("point(0,-T*50,0-W/100)")
        py_send("point(0,T*50,0-W/100)")
        py_send("point(0,T*50,W+W/100)")
        py_send("point(0,-T*50,W+W/100)")
        py_send("*duplicate_reset")
        py_send("*set_duplicate_repetitions  1")
        py_send("*duplicate_surfaces 22 # ")
        py_send("*duplicate_reset ")
        py_send("*set_duplicate_translation x -L/4")
        py_send("*set_duplicate_repetitions 4 ")
        py_send("*duplicate_surfaces 22 to 23 #")
        
def intersect(s1,s2) :        
    py_send("*intersect_surface "+str(s1)+","+str(s2))
    
def intersect_surfaces() :  
    HGL,TGL=py_get_float("HGL"),py_get_float("TGL") # HGL, TGL : Length of gutter at Head and Tail
    L=py_get_float("L")
    TGL=L-TGL
    HQL,TQL=L/4, 3*L/4   # Quarter points of gutter at Head and Tail
#    print "  Qurter points & length of gutter ===",HQL,HGL,TGL,TQL
    L1=[7,8,9,10,14,18,11,15,19,12,16,20,13,17,21]
    L2=[1,5,3,1,5,3,1,5,3,2,6,4,2,6,4]
    T1=[22,23,24,28,25,29,26,30,27,31]
    T2=[1,2,1,2,5,6,3,4,3,4] 
    if(HGL>HQL):
       T2[2],T2[3]=1,2
    if(HGL<=HQL):        
       T2[2],T2[3]=5,6
    if(TGL<TQL):
       T2[6],T2[7]=3,4
    if(TGL>=TQL):
       T2[6],T2[7]=5,6     
    print "T2 ====",T2     
    py_send("set_relative_tol 0.1")
    py_send("set_surfint_space off")
    py_send("set_surfint_trim1 on")
    py_send("set_surfint_trim2 off")
    
    for i in range(0,len(L1)) :
        intersect(L1[i],L2[i])
        print "Create Longi Path ",L1[i],L2[i]
    for i in range(0,len(T1)) :
        intersect(T1[i],T2[i])
        print "Create Trans Path ",T1[i],T2[i]

def select_surf(s,set) :
     py_send("*select_clear")
     py_send("*select_nodes ",str(s))
     py_send("*select_elements_nodes all_selected")
     py_send("*store_elements "+str(set)+" all_selected")
        
def select_section() : 
     s_l=[7,10,11,12,13]
     set_l=["sec_lle","sec_llq","sec_lc","sec_lrq","sec_lre"]
     s_t=[22,24,25,26,27]       
     set_t=["sec_tf","sec_tfq","sec_tc","sec_trq","sec_tr"]     
     py_send("*select_method_surface_dist")
     py_send("*set_select_distance T/1.9")
     py_send("*select_clear") 
     for i in range(0,len(s_l)) :
        select_surf(s_l[i],set_l[i])
        print "Create Longi section set ",s_l[i],set_l[i]
     for i in range(0,len(s_t)) :
        select_surf(s_t[i],set_t[i])
        print "Create Trans section set ",s_t[i],set_t[i]       
            
def main() :
    create_surface()
    py_send("*select_elements  all_existing")
    py_send("*invisible_selected ")
    select_section()
    intersect_surfaces()
    py_send("*select_method_single")
    py_send("*remove_unused_points")
    py_send("*renumber_all")
#    py_send("*remove_surfaces all_existing")
    
if __name__ == '__main__':
    py_connect("",40007)
    main()
    py_disconnect()