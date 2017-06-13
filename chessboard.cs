//
//
//Created by Deepfind From NCEPU  on 2017-6-12
//
//
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Algorithm
{
    class Program
    {
        static int nCount = 0;
        static int [,]Matrix;

        static void chessBoard(int tr, int tc, int dr, int dc, int size)
        {
            int s, t;
            if (1 == size) return;

            s = size / 2; //The number of grid the matrix's edge
            t = ++nCount;

            //locate the special  grid on bottom right corner
            if (dr < tr + s && dc < tc + s)
            {
                chessBoard(tr, tc, dr, dc, s);
            }
            else
            {
                Matrix[tr + s - 1,tc + s - 1] = t;
                chessBoard(tr, tc, tr + s - 1, tc + s - 1, s);
            }

            //locate the special  grid on bottom left corner
            if (dr < tr + s && dc >= tc + s)
            {
                chessBoard(tr, tc + s, dr, dc, s);
            }
            else
            {
                Matrix[tr + s - 1,tc + s] = t;
                chessBoard(tr, tc + s, tr + s - 1, tc + s, s);
            }

            //locate the special  grid on top right corner
            if (dr >= tr + s && dc < tc + s)
            {
                chessBoard(tr + s, tc, dr, dc, s);
            }
            else
            {
                Matrix[tr + s,tc + s - 1] = t;
                chessBoard(tr + s, tc, tr + s, tc + s - 1, s);
            }

            //locate the special  grid on top left corner
            if (dr >= tr + s && dc >= tc + s)
            {
                chessBoard(tr + s, tc + s, dr, dc, s);
            }
            else
            {
                Matrix[tr + s,tc + s] = t;
                chessBoard(tr + s, tc + s, tr + s, tc + s, s);
            }
        }
        static void Main(string[] args)
        {
            int size, r, c, row, col;

            size=int.Parse(Console.ReadLine());
            Matrix = new int[int.Parse(Math.Pow(2,size).ToString()), int.Parse(Math.Pow(2, size).ToString())];
            row = int.Parse(Console.ReadLine());
            col = int.Parse(Console.ReadLine());
            size = int.Parse(Math.Pow(2, size).ToString());
            chessBoard(0, 0, row, col, size);
            for (r = 0; r < size; r++)
            {
                for (c = 0; c < size; c++)
                {
                    Console.Write(Matrix[r,c].ToString()+" ");
                }
                Console.WriteLine();
            }

            Console.ReadLine();
        }
    }
}
