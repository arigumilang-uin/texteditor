package com.mycompany.texteditor;
import java.util.Scanner;
import java.util.Stack;

public class Texteditor {
    private String currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public Texteditor() {
        currentText = "";
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void show() {
        System.out.println("Teks saat ini:");
        System.out.println(currentText.isEmpty() ? "(kosong)" : currentText);
    }

    public void write(String newText) {
        undoStack.push(currentText); 
        currentText += newText; 
        redoStack.clear(); 
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText); 
            currentText = undoStack.pop(); 
        } else {
            System.out.println("Tidak ada aksi untuk di-undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText); 
            currentText = redoStack.pop(); 
        } else {
            System.out.println("Tidak ada aksi untuk di-redo.");
        }
    }

    public static void main(String[] args) {
        Texteditor editor = new Texteditor();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Text Editor - Pilih perintah: show, write, undo, redo, exit");
        do {
            System.out.print("Masukkan perintah: ");
            command = scanner.nextLine();

            switch (command) {
                case "show":
                    editor.show();
                    break;
                case "write":
                    System.out.print("Masukkan teks yang ingin ditulis: ");
                    String newText = scanner.nextLine();
                    editor.write(newText);
                    break;
                case "undo":
                    editor.undo();
                    break;
                case "redo":
                    editor.redo();
                    break;
                case "exit":
                    System.out.println("Keluar dari Text Editor.");
                    break;
                default:
                    System.out.println("Perintah tidak dikenal.");
            }
        } while (!command.equals("exit"));

        scanner.close();
    }
}
