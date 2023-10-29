import numpy as np
import scipy.interpolate as si
import matplotlib.pyplot as plt

def bspline(cv, n=100, degree=3):

    cv = np.asarray(cv)
    count = cv.shape[0]
    degree = np.clip(degree,1,count-1)
    kv = np.array([0]*degree + list(range(count-degree+1)) + [count-degree]*degree,dtype='int')
    u = np.linspace(0,(count-degree),n)
    return np.array(si.splev(u, (kv,cv.T,degree))).T

cv = np.array([[ 50,  25],
   [ 59,  29],
   [ 67,  10],
   [ 77,   32],
   [ 80,   9],
   [ 90,   24]])
plt.figure(facecolor='#000000')
plt.plot(cv[:,0],cv[:,1], 'o--', label='Control Points')

p = bspline(cv,n=100,degree=2)
x,y = p.T
plt.plot(x,y,'k-',label='Quadratic B Spline',color='w')

plt.minorticks_on()
plt.legend()
plt.xlim(35, 70)
plt.ylim(0, 30)
plt.gca().set_aspect('equal', adjustable='box')
plt.axis('off')

plt.show()
