from tkinter import *

def login():
    # Create a new frame for the login form
    pp = Frame(top, bg="purple", height=1000, width=800)
    pp.pack(fill=BOTH, expand=True)
    
    label = Label(pp, text="WELCOME", bg="white", fg="blue")
    label.place(x=300, y=10)
    
    box1 = Entry(pp, text="", font=('calligrapher', 16, 'italic'), fg="black")
    box1.place(x=250, y=100, width=200, height=35)
    
    def close_login():
        pp.destroy()
    
    pp1 = Button(pp, text="Back", font=('Timenewroman', 16, 'italic'), command=close_login)
    pp1.place(x=400, y=200, width=200, height=50)

top = Tk()
top.geometry("1400x800")
top.title("Text")
top.config(bg="pink")

welcome = Label(top, text="ENTER", font=('TimeNewRoman', 16, 'italic'), bg="indigo", fg="silver")
welcome.place(x=300, y=45)

box = Entry(top, text="", font=('calligrapher', 16, 'italic'), fg="black")
box.place(x=300, y=100, width=200, height=35)

button = Button(top, text="Login", font=('Timenewroman', 16, 'italic'), bg='blue', fg='white', activebackground="blue", activeforeground="red", command=login)
button.place(x=400, y=200, width=200, height=50)

top.mainloop()
