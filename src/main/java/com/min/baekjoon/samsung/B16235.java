package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B16235 {
    static int N, M, K;
    static int food[][];
    static int land[][];
    static TreeManager treeManager;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        food = new int[N][N];
        land = new int[N][N];
        treeManager = new TreeManager(N);

        for (int i = 0; i < N; i++) {
            Arrays.fill(land[i], 5);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int old = Integer.parseInt(st.nextToken());
            Tree tree = new Tree(r, c, old);
            treeManager.add(tree);
        }

        for (int i = 0; i < K; i++) {
            treeManager.yearPass();
        }

        System.out.println(treeManager.aliveTreeCnt);

//        int answer = 0;
//        for (int i = 0 ; i < N ; i++) {
//            for (int j = 0 ; j < N ; j++) {
//                Tree tree = treeManager.trees[i*N + j];
//                while (tree != null) {
//
//                    if (!tree.die) {
//                        answer++;
//                    }
//                    tree = tree.next;
//                }
//            }
//        }
//
//        System.out.println(answer);
    }

    public static class TreeManager {
        Tree[] trees;
        int aliveTreeCnt;

        public TreeManager(int N) {
            this.trees = new Tree[N * N];
            aliveTreeCnt = 0;
        }

        public void add(Tree tree) {
            int idx = tree.hashCode();
            if (trees[idx] != null) {
                Tree cur = trees[idx];
                if (cur.old >= tree.old) {
                    trees[idx] = tree;
                    tree.next = cur;
                } else {
                    while (cur.next != null) {
                        if (cur.next.old >= tree.old) {
                            Tree temp = cur.next;
                            cur.next = tree;
                            tree.next = temp;
                            aliveTreeCnt++;
                            return;
                        }
                        cur = cur.next;
                    }
                    cur.next = tree;
                }
            } else {
                trees[idx] = tree;
            }
            aliveTreeCnt++;
        }

        public void addAll(List<Tree> trees) {
            for (Tree tree : trees) {
                add(tree);
            }
        }

        public void foodSupply() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    land[i][j] += food[i][j];
                }
            }
        }

        public void yearPass() {
            //봄, 여름
            for (int i = 0; i < trees.length; i++) {
                if (trees[i] == null) {
                    continue;
                }
                int[] dieResult = new int[2];
                Tree temp = trees[i];
                Tree before = trees[i];
                int curR = trees[i].r;
                int curC = trees[i].c;

                if (land[temp.r][temp.c] - temp.old < 0) {
                    // 죽음
                    dieResult = temp.die();
                    trees[i] = null;
                    temp = null;
                } else {
                    land[temp.r][temp.c] -= temp.old;
                    temp.old += 1;
                    temp = temp.next;
                }

                while (temp != null) { // 나무 죽일때 추가 작업 필요(연결 정리)
                    if (land[temp.r][temp.c] - temp.old < 0) {
                        // 죽음
                        dieResult = temp.die();
                        temp = null;
                        before.next = null;
                    } else {
                        land[temp.r][temp.c] -= temp.old;
                        temp.old += 1;
                        before = temp;
                        temp = temp.next;
                    }
                }
                land[curR][curC] += dieResult[0];
                aliveTreeCnt -= dieResult[1];
            }
            //가을
            List<Tree> breeding = new ArrayList<>();
            for (int i = 0; i < trees.length; i++) {
                Tree temp = trees[i];
                while (temp != null) {
                    if (temp.old % 5 == 0) { //번식
                        breeding.addAll(temp.breed());
                    }
                    temp = temp.next;
                }
            }

            addAll(breeding);
            //겨울
            foodSupply();
        }
    }

    public static class Tree {
        private static final int[] dr = {-1, 1, 0, 0, -1, 1, 1, -1}; //상하좌우,왼위-왼아래-오아래-오위
        private static final int[] dc = {0, 0, -1, 1, -1, -1, 1, 1};
        int r;
        int c;
        int old;
        boolean die;
        Tree next;

        public Tree(int r, int c, int old) {
            this.r = r;
            this.c = c;
            this.old = old;
            this.next = null;
            this.die = false;
        }

        public List<Tree> breed() {
            List<Tree> breeding = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                if (r + dr[i] < 0 || r + dr[i] >= N || c + dc[i] < 0 || c + dc[i] >= N) {
                    continue;
                }
                breeding.add(new Tree(r + dr[i], c + dc[i], 1));
            }
            return breeding;
        }

        public int[] die() {
            int food = this.old / 2;
            int[] result = new int[2];
            result[0] = food;
            result[1] = 1;
            die = true;
            if (next != null) {
                int[] tmp = next.die();
                next = null;
                result[0] += tmp[0];
                result[1] += tmp[1];
            }
            return result;
        }

        @Override
        public int hashCode() {
            return (N * r) + c;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "r=" + r +
                    ", c=" + c +
                    ", old=" + old +
                    ", die=" + die +
                    '}';
        }
    }
}
