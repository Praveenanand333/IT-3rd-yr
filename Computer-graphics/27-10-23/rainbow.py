import pygame
from pygame.locals import *
from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *

def bez(p0, p1, p2):
    for t1 in range(0,9000,1):
        t1 = t1/9000
        x = (1 - t1) ** 2 * p0[0] + 2 * (1 - t1) * t1 * p1[0] + t1 ** 2 * p2[0]
        y = (1 - t1) ** 2 * p0[1] + 2 * (1 - t1) * t1 * p1[1] + t1 ** 2 * p2[1]
        if x<0:
            x=-x
        if y<0:
            y=-y
        glVertex2f(x, y)
       # glVertex2f(x+1, y+1)
        #glVertex2f(x-1, y-1)
        #glVertex2f(x-1, y+1)
        #glVertex2f(x+1, y-1)



def init():
    glClearColor(0.0, 0.0, 0.0, 1.0)  # Set clear color to black
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    gluOrtho2D(0, 1200, 0, 800)
    glMatrixMode(GL_MODELVIEW)

def main():
    pygame.init()
    display = (1200, 800)
    pygame.display.set_mode(display, DOUBLEBUF | OPENGL)
    init()

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        
        glClear(GL_COLOR_BUFFER_BIT)

        # Points for drawing the line
        glBegin(GL_POINTS)
        glColor3f(148/255, 0/255, 211/255)
        bez((200, 200),
            (600,800),
            (1000, 200))
        glColor3f(75/255, 0/255, 130/255)
        bez((190, 200),
            (600,815),
            (1010, 200))
        glColor3f(0/255, 0/255, 255/255)
        bez((180, 200),
            (600,830),
            (1020, 200))
        glColor3f(0/255, 255/255, 0/255)
        bez((170, 200),
            (600,845),
            (1030, 200))
        glColor3f(255/255, 255/255, 0/255)
        bez((160, 200),
            (600,860),
            (1040, 200))
        glColor3f(255/255, 127/255, 0/255)
        bez((150, 200),
            (600,875),
            (1050, 200))
        glColor3f(255/255, 0/255, 0/255)
        bez((140, 200),
            (600,890),
            (1060, 200))
        glEnd()

        pygame.display.flip()
        pygame.time.wait(10)

    pygame.quit()

if __name__ == "__main__":
    main()
