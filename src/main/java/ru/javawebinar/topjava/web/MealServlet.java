package ru.javawebinar.topjava.web;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealServlet extends HttpServlet {

  private static final Logger log = getLogger(UserServlet.class);

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    log.debug("redirect to meals");
    List<MealTo> meals = MealsUtil
        .filteredByStreams(MealsUtil.meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
    request.setAttribute("MealsTo", meals);
    request.getRequestDispatcher("/meals.jsp").forward(request, response);
  }
}
