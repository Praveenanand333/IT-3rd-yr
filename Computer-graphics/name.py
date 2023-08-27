import glfw
from OpenGL.GL import *
import math

def draw_line(x1, y1, x2, y2):
    glBegin(GL_POINTS)
    glVertex2f(x1, y1)
    glVertex2f(x2, y2)
    glEnd()

def dda(x1, y1, x2, y2):
    dx = x2 - x1
    dy = y2 - y1
    steps = max(abs(dx), abs(dy))
    x_increment = dx / steps
    y_increment = dy / steps
    x, y = x1, y1

    for _ in range(steps + 1):
        draw_line(x, y, x, y)
        x += x_increment
        y += y_increment

def bresenham(x1, y1, x2, y2):
    dx = abs(x2 - x1)
    dy = abs(y2 - y1)
    sx = -1 if x1 > x2 else 1
    sy = -1 if y1 > y2 else 1
    err = dx - dy

    while x1 != x2 or y1 != y2:
        draw_line(x1,y1,x1,y1)
        e2 = 2 * err
        if e2 > -dy:
            err -= dy
            x1 += sx
        if e2 < dx:
            err += dx
            y1 += sy


def main():
    if not glfw.init():
        return

    window = glfw.create_window(800, 600, "OpenGL Name Drawing", None, None)
    if not window:
        glfw.terminate()
        return

    glfw.make_context_current(window)
    glOrtho(0, 800, 0, 600, -1, 1)
    glClearColor(1, 1, 1, 1)

    while not glfw.window_should_close(window):
        glfw.poll_events()
        
        glClear(GL_COLOR_BUFFER_BIT)
        
        glColor3f(0, 0, 0)
        
        # Draw "P" using DDA
        dda(50, 100, 50, 200)
        dda(50, 200, 100, 200)
        dda(100, 200, 100, 150)
        dda(100, 150, 50, 150)

        # Draw "R" using Bresenham's
        dda(110, 100, 110, 200)
        dda(110, 200, 160, 200)
        dda(160, 200, 160, 150)
        dda(160, 150, 110, 150)
        bresenham(160,100,110,150)


        # Draw "A" using DDA
        bresenham(170, 150, 195, 200)
        dda(170, 150, 220, 150)
        bresenham(195, 200, 220, 150)
        dda(170, 100, 170, 150)
        dda(220, 100, 220, 150)

        glfw.swap_buffers(window)

    glfw.terminate()

if __name__ == "__main__":
    main()
