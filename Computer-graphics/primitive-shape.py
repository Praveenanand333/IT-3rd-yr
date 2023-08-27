import matplotlib.pyplot as plt

# Initialize canvas
width, height = 40, 40
canvas = [[' ' for _ in range(width)] for _ in range(height)]

# Bresenham's Line Drawing Algorithm
def draw_line(x1, y1, x2, y2):
    dx = abs(x2 - x1)
    dy = abs(y2 - y1)
    sx = -1 if x1 > x2 else 1
    sy = -1 if y1 > y2 else 1
    err = dx - dy

    while x1 != x2 or y1 != y2:
        canvas[y1][x1] = '*'
        e2 = 2 * err
        if e2 > -dy:
            err -= dy
            x1 += sx
        if e2 < dx:
            err += dx
            y1 += sy

# Midpoint Circle Drawing Algorithm
def draw_circle(center_x, center_y, radius):
    x = radius
    y = 0
    err = 0

    while x >= y:
        if 0 <= center_x + x < width and 0 <= center_y + y < height:
            canvas[center_y + y][center_x + x] = '*'
        if 0 <= center_x + y < width and 0 <= center_y + x < height:
            canvas[center_y + x][center_x + y] = '*'
        if 0 <= center_x - x < width and 0 <= center_y + y < height:
            canvas[center_y + y][center_x - x] = '*'
        if 0 <= center_x - y < width and 0 <= center_y + x < height:
            canvas[center_y + x][center_x - y] = '*'
        if 0 <= center_x - x < width and 0 <= center_y - y < height:
            canvas[center_y - y][center_x - x] = '*'
        if 0 <= center_x - y < width and 0 <= center_y - x < height:
            canvas[center_y - x][center_x - y] = '*'
        if 0 <= center_x + x < width and 0 <= center_y - y < height:
            canvas[center_y - y][center_x + x] = '*'
        if 0 <= center_x + y < width and 0 <= center_y - x < height:
            canvas[center_y - x][center_x + y] = '*'

        if err <= 0:
            y += 1
            err += 2 * y + 1
        if err > 0:
            x -= 1
            err -= 2 * x + 1

# Draw a square
draw_line(4, 4, 12, 4)
draw_line(4, 4, 4, 12)
draw_line(12, 4, 12, 12)
draw_line(4, 12, 12, 12)

# Draw a rectangle
draw_line(18, 4, 30, 4)
draw_line(18, 4, 18, 12)
draw_line(30, 4, 30, 12)
draw_line(18, 12, 30, 12)

# Draw a circle
draw_circle(10, 30, 8)

# Draw a triangle
draw_line(24, 28, 20, 34)
draw_line(20, 34, 28, 34)
draw_line(28, 34, 24, 28)

# Display the canvas
for row in canvas:
    print(' '.join(row))

# Plot the canvas using Matplotlib
plt.imshow([[1 if pixel == '*' else 0 for pixel in row] for row in canvas], cmap='gray')
plt.show()
