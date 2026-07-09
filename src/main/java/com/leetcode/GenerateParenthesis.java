package com.leetcode;

import java.util.LinkedList;
import java.util.List;

public class GenerateParenthesis {

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(3));
    }

    void compute(List<String> res, int left, int right, StringBuilder data) {
        if (left == right && left == 0) {
            res.add(data.toString());
            return;
        }

        boolean isLeft = left > 0;
        boolean isRight = left < right;

        if (isLeft) {
            data.append('(');
            compute(res, left - 1, right, data);
            data.deleteCharAt(data.length() - 1);

        }
        if (isRight) {
            data.append(')');
            compute(res, left, right - 1, data);
            data.deleteCharAt(data.length() - 1);
        }

    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        compute(res, n, n, new StringBuilder());
        return res;
    }
}
