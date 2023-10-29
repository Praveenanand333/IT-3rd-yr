import pygame
from OpenGL.GL import shaders
from pygame.locals import *
from OpenGL.GL import *
from OpenGL.GLU import *

colors1 = [(1, 0, 0)] * 12
colors2 = [(0, 1, 0)] * 12
colors3 = [(0, 0, 1)] * 12

verticies = (
    ( 1, -1, -1), # 0
    ( 1,  1, -1), # 1
    (-1,  1, -1), # 2
    (-1, -1, -1), # 3
    ( 1, -1,  1), # 4
    ( 1,  1,  1), # 5
    (-1, -1,  1), # 6
    (-1,  1,  1), # 7
    )
verticies2 = (
    ( 1, -1, -5), # 0
    ( 1,  1, -5), # 1
    (-1,  1, -5), # 2
    (-1, -1, -5), # 3
    ( 1, -1, -3), # 4
    ( 1,  1, -3), # 5
    (-1, -1, -3), # 6
    (-1,  1, -3), # 7
    )

verticies3 = (
    ( 2.5, -1, -10), # 0
    ( 1,  1, -10), # 1
    (-1,  1, -10), # 2
    (-2.5, -1, -10), # 3
    ( 2.5, -1, -7), # 4
    ( 1,  1, -7), # 5
    (-2.5, -1, -7), # 6
    (-1,  1, -7), # 7
    )
surfaces = (
    (0,1,2,3),
    (3,2,7,6),
    (6,7,5,4),
    (4,5,1,0),
    (1,5,7,2),
    (4,0,3,6),
    )
normals = [
    ( 0,  0, -1),  # surface 0
    (-1,  0,  0),  # surface 1
    ( 0,  0,  1),  # surface 2
    ( 1,  0,  0),  # surface 3
    ( 0,  1,  0),  # surface 4
    ( 0, -1,  0)   # surface 5
]
edges = (
    (0,1),
    (0,3),
    (0,4),
    (2,1),
    (2,3),
    (2,7),
    (6,3),
    (6,4),
    (6,7),
    (5,1),
    (5,4),
    (5,7),
    )
def Cube():
    glBegin(GL_QUADS)
    for i_surface, surface in enumerate(surfaces):
        x = 0
        glNormal3fv(normals[i_surface])
        for vertex in surface:
            x += 1
            glColor3fv(colors1[x])
            glVertex3fv(verticies[vertex])
    glEnd()
    glColor3fv(colors1[0])
    glBegin(GL_LINES)
    for edge in edges:
        for vertex in edge:
            glVertex3fv(verticies[vertex])
    glEnd()
    glMaterialfv(GL_FRONT, GL_SPECULAR, (1, 1, 1, 1))
    glMaterialfv(GL_FRONT, GL_SHININESS, 1)
    glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE)

def Cube2():
    glBegin(GL_QUADS)
    for i_surface, surface in enumerate(surfaces):
        x = 0
        glNormal3fv(normals[i_surface])
        for vertex in surface:
            x += 1
            glColor3fv(colors2[x])
            glVertex3fv(verticies2[vertex])
    glEnd()
    glColor3fv(colors2[0])
    glBegin(GL_LINES)
    for edge in edges:
        for vertex in edge:
            glVertex3fv(verticies2[vertex])
    glEnd()
    glMaterialfv(GL_FRONT, GL_SPECULAR, (1, 1, 1, 1))
    glMaterialfv(GL_FRONT, GL_SHININESS, 1)
    glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE)

def Cube3():
    glBegin(GL_QUADS)
    for i_surface, surface in enumerate(surfaces):
        x = 0
        glNormal3fv(normals[i_surface])
        for vertex in surface:
            x += 1
            glColor3fv(colors3[x])
            glVertex3fv(verticies3[vertex])
    glEnd()
    glColor3fv(colors3[0])
    glBegin(GL_LINES)
    for edge in edges:
        for vertex in edge:
            glVertex3fv(verticies3[vertex])
    glEnd()
    glMaterialfv(GL_FRONT, GL_SPECULAR, (1, 1, 1, 1))
    glMaterialfv(GL_FRONT, GL_SHININESS, 1)
    glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE)


def main():
    global surfaces
    pygame.init()
    display = (800, 600)
    pygame.display.set_mode(display, DOUBLEBUF|OPENGL)
    clock = pygame.time.Clock()
    glMatrixMode(GL_PROJECTION)
    gluPerspective(45, (display[0]/display[1]), 0.1, 50.0)
    glMatrixMode(GL_MODELVIEW)
    glTranslatef(0, 0, -10)
    glRotatef(-20, 1, 0, 0)
    glRotatef(-30, 0, 1, 0)
    
    #glLight(GL_LIGHT0, GL_POSITION,  (0.6, 0., .3, 1))  # point light
    #glLightfv(GL_LIGHT0, GL_AMBIENT, (0, 0, 1, 1))
    #glLightfv(GL_LIGHT0, GL_DIFFUSE, (1, 1, 1, 1))
    glEnable(GL_LIGHTING)
    glEnable(GL_LIGHT0)
    glEnable(GL_COLOR_MATERIAL)
    glEnable(GL_DEPTH_TEST)
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT)
        Cube()
        Cube2()
        Cube3()
        pygame.display.flip()
        clock.tick(60)
main()