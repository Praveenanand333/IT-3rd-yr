import glfw
from OpenGL.GL import *
import math

def draw_circle(radius, num_segments):
    glBegin(GL_LINE_LOOP)
    for i in range(num_segments):
        theta = 2.0 * math.pi * float(i) / num_segments
        x = radius * math.cos(theta)
        y = radius * math.sin(theta)
        glVertex2f(x, y)
    glEnd()

def draw_olympic_logo():
    glClear(GL_COLOR_BUFFER_BIT)
    
    glPushMatrix()
    
    # Draw blue ring
    glColor3f(0.0, 0.0, 1.0)
    glTranslatef(-0.2, 0.0, 0.0)
    draw_circle(0.1, 100)
    
    # Draw black ring
    glColor3f(0.0, 0.0, 0.0)
    glTranslatef(0.2, 0.0, 0.0)
    draw_circle(0.1, 100)

    # Draw red ring
    glColor3f(1.0, 0.0, 0.0)
    glTranslatef(0.2, 0.0, 0.0)
    draw_circle(0.1, 100)
    
    glPopMatrix()

    # Draw yellow ring
    glColor3f(1.0, 1.0, 0.0)
    glPushMatrix()
    glTranslatef(-0.1, -0.15, 0.0)
    draw_circle(0.1, 100)
    glPopMatrix()

    # Draw green ring
    glColor3f(0.0, 1.0, 0.0)
    glPushMatrix()
    glTranslatef(0.1, -0.15, 0.0)
    draw_circle(0.1, 100)
    glPopMatrix()

def main():
    if not glfw.init():
        return

    window = glfw.create_window(800, 600, "Olympic Logo", None, None)
    if not window:
        glfw.terminate()
        return

    glfw.make_context_current(window)

    glOrtho(-1, 1, -1, 1, -1, 1)
    glClearColor(1, 1, 1, 1)

    while not glfw.window_should_close(window):
        glfw.poll_events()
        draw_olympic_logo()
        glfw.swap_buffers(window)

    glfw.terminate()

if __name__ == "__main__":
    main()
