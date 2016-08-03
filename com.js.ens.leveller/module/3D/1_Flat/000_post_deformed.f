      subroutine upstno(nqcode,nodeid,valno,nqncomp,nqtype,
     *                  nqaver,nqcomptype,nqdatatype,
     *                  nqcompname)
#ifdef _IMPLICITNONE
      implicit none
#else
      implicit logical (a-z)
#endif
c     ** Start of generated type statements **
      integer nodeid, nqaver, nqcode, nqcomptype, nqdatatype, nqncomp
      integer nqtype
      real*8 valno
C    User added variables      
      real*8 valno1, valno2
      integer i
c     ** End of generated type statements **
c
      dimension valno(*)
      character*24 nqcompname(*)
C    User added variables            
      dimension valno1(3), valno2(3)
c
c input:  nqcode      user nodal post code , e.g. -1
c         nodeid      node id
c         nqcompname  not used (future expansion)
c
c output: valno()     nodal values
c                     real/imag valno(        1:  nqncomp) real
c                               valno(nqncomp+1:2*nqncomp) imag
c                     magn/phas valno(        1:  nqncomp) magn
c                               valno(nqncomp+1:2*nqncomp) phas
c         nqncomp     number of values in valno
c         nqtype      0 = scalar
c                     1 = vector
c         nqaver      only for DDM 0 = sum over domains
c                                  1 = average over domains
c         nqcomptype  0 = global coordinate system (x,y,z)
c                     1 = shell (top,bottom,middle)
c                     2 = order (1,2,3)
c         nqdatatype  0 = default
c                     1 = modal
c                     2 = buckle
c                     3 = harmonic real
c                     4 = harmonic real/imaginary
c                     5 = harmonic magnitude/phase
c
c
c to obtain nodal values to be used in this subroutine from
c the marc data base the genral subroutine NODVAR is available:
c
c call nodvar(icod,nodeid,valno,nqncomp,nqdatatype)
c
c output: valno
c         nqncomp
c         nqdatatype
c
c input:  nodeid
c         icod
c            0='Coordinates              '
c            1='Displacement             '
c            2='Rotation                 '
c            3='External Force           '
c            4='External Moment          '
c            5='Reaction Force           '
c            6='Reaction Moment          '
c            7='Fluid Velocity           '
c            8='Fluid Pressure           '
c            9='External Fluid Force     '
c           10='Reaction Fluid Force     '
c           11='Sound Pressure           '
c           12='External Sound Source    '
c           13='Reaction Sound Source    '
c           14='Temperature              '
c           15='External Heat Flux       '
c           16='Reaction Heat Flux       '
c           17='Electric Potential       '
c           18='External Electric Charge '
c           19='Reaction Electric Charge '
c           20='Magnetic Potential       '
c           21='External Electric Current'
c           22='Reaction Electric Current'
c           23='Pore Pressure            '
c           24='External Mass Flux       '
c           25='Reaction Mass Flux       '
c           26='Bearing Pressure         '
c           27='Bearing Force            '
c           28='Velocity                 '
c           29='Rotational Velocity      '
c           30='Acceleration             '
c           31='Rotational Acceleration  '
c           32='Modal Mass               '
c           33='Rotational Modal Mass    '
c           34='Contact Normal Stress    '
c           35='Contact Normal Force     '
c           36='Contact Friction Stress  '
c           37='Contact Friction Force   '
c           38='Contact Status           '
c           39='Contact Touched Body     '
c           40='Herrmann Variable        '
c           41='Density of Solid         '
c           42='Mass Flow Rate of Gas    '
c           43='Rt of Chnge of Pyrolysis '
c           44='Rt of Chnge of Liquid Den'
c           45='Rt of Chnge of Coke Densi'
c
c......................................... Begin User Coding

c .....  Average over domain when DDM run
      nqaver=1 
            
      
      if (nqcode.eq.-1) then
c... pick up cordinate and store in valno1
      call nodvar(0,nodeid,valno1,nqncomp,nqdatatype)
c... pick up displacement and store in valno2
      call nodvar(1,nodeid,valno2,nqncomp,nqdatatype)
c... add coordinate and displacement
C      write(6,*)  'nqncomp  ===', nqncomp
      do 1 i = 1, nqncomp
      valno(i)=valno1(i)+valno2(i)
C      write(6,*)  'result ===',nodeid, i,valno(i)
    1 continue
c... indicate that valno represents a vector
      nqtype=1
      nqcomptype=0
      end if
c......................................... End User Coding

      return
      end
