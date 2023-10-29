import pygame
from pygame.locals import *
from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *

def quad_bez(p0, p1, p2):
    for t1 in range(0,500,1):
        t1 = t1/500
        x = (1 - t1) ** 2 * p0[0] + 2 * (1 - t1) * t1 * p1[0] + t1 ** 2 * p2[0]
        y = (1 - t1) ** 2 * p0[1] + 2 * (1 - t1) * t1 * p1[1] + t1 ** 2 * p2[1]
        if x<0:
            x=-x
        if y<0:
            y=-y
        glVertex2f(x, y)


def init():
    glClearColor(0.0, 0.0, 0.0, 1.0)  # Set clear color to black
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    gluOrtho2D(0, 800, 0, 600)
    glMatrixMode(GL_MODELVIEW)

def main():
    pygame.init()
    display = (800, 600)
    pygame.display.set_mode(display, DOUBLEBUF | OPENGL)
    init()

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        
        glClear(GL_COLOR_BUFFER_BIT)
        glColor3f(1.0, 1.0, 1.0)

        # Points for drawing the line
        glBegin(GL_POINTS)
        p0 = (100, 200)   # beginning  point
        p1 = (400,400)   # Control point
        p2 = (600, 200)   # finishing point
        quad_bez(p0,p1,p2)
        glEnd()

        pygame.display.flip()
        pygame.time.wait(10)

    pygame.quit()

if __name__ == "__main__":
    main()
