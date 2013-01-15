<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="firstUrl" value="/list/${nodeType}/0" />
<c:url var="lastUrl" value="/list/${nodeType}/${totalPages}" />
<c:url var="prevUrl" value="/list/${nodeType}/${currentIndex - 1}" />
<c:url var="nextUrl" value="/list/${nodeType}/${currentIndex + 1}" />


 <div class="main-container">
            <div class="main wrapper clearfix">
            <c:choose>
  <c:when test="${not empty people}">
  
<h2>${nodeType} List</h2>
  <div class="pagination">
    <ul>
        <c:choose>
            <c:when test="${currentIndex == 0}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/list/${nodeType}/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == deploymentLog.totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
 
    <ol class="search-results">
      <c:forEach items="${people}" var="people">
        <li>
          <div class="search-result-details">
           <table>
   <tr>
<td><a href= "/node/${nodeType}/${people.nodeId}">Display Name</a></td>
         <td> <a href= "/node/${nodeType}/${people.nodeId}">${people.displayName}</a></td>
    </tr>   
<tr>
        <td>Node Type</td>
        <td>${people.nodeType}</td>
        </tr>
<tr>
        <td>First Created</td>
        <td>${people.firstCreated}</td>
    </tr>
</table>        
           
 
          </div>
        </li>
      </c:forEach>
    </ol>
  </c:when>
  <c:otherwise>
    <h2>We encountered an error, sorry :(.</h2>
  </c:otherwise>
</c:choose>

   </div> <!-- #main -->
        </div> <!-- #main-container -->