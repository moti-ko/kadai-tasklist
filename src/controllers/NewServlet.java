package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        // Messageのインスタンスを生成する
        Message m = new Message();

        // データを代入する
        String content = "資料作成";
        m.setContent(content);

        //現時刻を取得する
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        m.setCreated_at(currentTime);
        m.setUpdated_at(currentTime);

        // DBに保存する
        em.persist(m);
        em.getTransaction().commit();

        // 自動採番されたIDの値を表示する
        response.getWriter().append(Integer.valueOf(m.getId()).toString());

        em.close();
    }

}
