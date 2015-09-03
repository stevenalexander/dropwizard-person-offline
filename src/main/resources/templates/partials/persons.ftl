<#include "../main.ftl">
<#macro page_body>

<h1>Persons</h1>

<#if persons?has_content>
<ul>
<#list persons as person>
  <li>
    <form action="/persons/${person.id}/delete" method="POST">
      <a href="/persons/${person.id}">${person.firstName} ${person.lastName}</a>
      <button type="submit" class="btn btn-link offline-hide">delete</button>
    </form>
  </li>
</#list>
</ul>
</#if>

<a href="/persons/add" class="offline-hide">Add Person</a>

</#macro>
