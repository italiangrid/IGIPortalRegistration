<%@ include file="/WEB-INF/jsp/init.jsp"%>
<script type="text/javascript">
	var lista;

	function printList() {
		$("#listaVo").html("");
		var output = "";
		var i = 0;
		while (i < lista.length) {

			output = output
					+ "<input name='resultList' type='hidden' value='" + lista[i]+ "' />"
					+ "<br/>";
			i++;
		}

		$("#result").html(output);
	} 

	function removeRole(role) {
		var i = 0;
		while (i < lista.length) {
			if (lista[i] == role) {
				lista.splice(i, 1);
				break;
			}
			i++;
		}
		if (lista.length == 0) {
			//$("#reset").html("<li id=\"sortable-delete\">Trascina qui per cancellare</li>");
			$("#reset").html("<li class=\"placeholder\">Insert your FQAN here</li>");
		}
		printList();
	}

	function addRole(role) {
		var i = 0;
		var check = true;
		while (i < lista.length) {
			if (lista[i] == role) {
				check = false;
				break;
			}
			i++;
		}
		if (check) {
			lista.push(role);
		} else {
			alert("Role already selected");
		}
		printList();
		return check;
	}

	function updateList() {

		var arr = $("#reset li");

		var newlist = new Array();

		var i = 0;

		while (i < arr.length) {
			if (arr[i].innerHTML == "Insert your FQAN here") {
				break;
			}
			//alert(arr[i].innerHTML);
			newlist.push(arr[i].innerHTML);
			i++;
		}

		lista = newlist;
		printList();

	}

	$(function() {
		$("#catalog").accordion();
		$("#catalog li").draggable({
			cursor : "move",
			appendTo : "body",
			helper : "clone"
		});
		$("#cart ol").droppable({
			activeClass : "ui-state-default",
			hoverClass : "ui-state-hover",
			accept : ":not(.ui-sortable-helper)",
			drop : function(event, ui) {
				if (addRole(ui.draggable.text())) {
					$(this).find(".placeholder").remove();
					$("<li></li>").text(ui.draggable.text()).appendTo(this);

				}
			}
		}).sortable({
			items : "li:not(.placeholder)",
			sort : function() {
				// gets added unintentionally by droppable interacting with sortable
				// using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
				$(this).removeClass("ui-state-default");

			}
		});

		$('ul.sortable').sortable({
			tolerance : 'pointer',
			cursor : 'move',
			dropOnEmpty : true,
			connectWith : 'ul.sortable',
			update : function(event, ui) {
				if (this.id == 'sortable-delete') {
					// Remove the element dropped on #sortable-delete
					jQuery('#' + ui.item.attr('id')).remove();
					removeRole(ui.item.text());
					$(this).removeClass("ui-state-default");
				} else {
					// Update code for the actual sortable lists
					updateList();
				}

			}
		});
		$('ol.sortable').sortable({
			tolerance : 'pointer',
			cursor : 'move',
			dropOnEmpty : true,
			connectWith : 'ul.sortable',
			update : function(event, ui) {
				if (this.id == 'sortable-delete') {
					// Remove the element dropped on #sortable-delete
					jQuery('#' + ui.item.attr('id')).remove();
					removeRole(ui.item.text());
					$(this).removeClass("ui-state-default");
				} else {
					// Update code for the actual sortable lists
					updateList();
				}

			}
		});
	});

	function reset() {
		$("#result").html("");
		$("#reset").html("");
		output = "";
		output = "<li class=\"placeholder\">Insert your FQAN here</li>";
		$("#reset").html(output);
		lista = new Array();
	}

	function loadLista() {
		var arr = $("#reset li");

		var i = 0;

		while (i < arr.length) {
			if (arr[i].innerHTML != "Insert your FQAN here") {
				lista.push(arr[i].innerHTML);
			}
			i++;
		}

	}

	$(document).ready(function() {
		lista = new Array();
		loadLista();
	});
</script>
<style>
#products {
	float: left;
	width: 300px;
	margin-right: 2em;
}

#insertHere {
	margin: 0;
	padding: 1em 0 1em 3em;
	border: 1px;
	border-color: #ACDFA7;
	border-style: solid;
	background-color: #F4FDEF;
}

#cart {
	width: 300px;
	float: left;
}
/* style the list to maximize the droppable hitarea */
#cart ol {
	margin: 0;
	padding: 1em 0 1em 3em;
	border: 1px;
	border-color: #FC6;
	border-style: solid;
	background-color: #FFD;
}

ul#sortable-delete {
	margin: 0 0 3px 0;
	padding: 1em 0 1em 3em;
	border: 1px;
	border-color: red;
	border-style: solid;
	background-color: #FDD;
}
</style>

<portlet:actionURL var="addUserToVoActionUrl">
	<portlet:param name="myaction" value="addFqans" />
</portlet:actionURL>

<jsp:useBean id="userToVo" type="it.italiangrid.portal.dbapi.domain.UserToVo"
	scope="request" />



<aui:form name="addUserToVoForm"
	action="${addUserToVoActionUrl}">

	<aui:layout>
		<aui:fieldset>

			<%
				if (request.getParameter("userId") != null)
					pageContext.setAttribute("userId",
							request.getParameter("userId"));
				if (request.getParameter("idVo") != null)
					pageContext.setAttribute("idVo",
							request.getParameter("idVo"));
			%>

			<!-- <c:out value="userId ${userId}; idVo ${idVo};"></c:out> -->
			<aui:input name="userId" type="hidden" value="${userId}" />
			<aui:input name="idVo" type="hidden" value="${idVo}" />

			<h1>Modify VO Role</h1>
			<br />
			<strong>Help:</strong>
			<br />
			Drag and drop roles form green zone into yellow zone and order by your preferences. <br/>
			For erasing a role from your list drag and drop the role from yellow zone into red zone. <br/>
			<br />
			<div id="products">
				<div id="catalog">
					<h3>
						Roles
						<c:out value="${vo.vo}" />
					</h3>
					<div id="voList">
						<ul id="insertHere">

							<c:choose>
								<c:when test="${fn:length(fqans) > 0}">
									<c:forEach var="fqan" items="${fqans}">

										<li><c:out value="${fqan}" /></li>

									</c:forEach>
								</c:when>
								<c:otherwise>
									You not have FQAN for this VO.
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>

			<div id="cart">
				<h3 class="ui-widget-header">FQANs list</h3>
				<div class="ui-widget-content">

					<ul id="sortable-delete" class="sortable">Drag and drop here for erase role</ul>

					<ol id="reset" class="sortable">

						<c:choose>
							<c:when
								test="${(empty userToVo.fqans)||(userToVo.fqans == '')}">

								<li class="placeholder">Insert your FQAN here</li>
							</c:when>
							<c:otherwise>
								<c:set var="strings" value="${fn:split(userToVo.fqans,';')}" />
								<c:forEach var="fqan" items="${strings}">

									<li><c:out value="${fqan}" />
									</li>

								</c:forEach>
							</c:otherwise>
						</c:choose>
					</ol>
					<br />
				</div>

				<aui:a href="#vo" onclick="reset();">Erase list</aui:a>
			</div>

			<div id="result">

				<c:if test="${(! empty userToVo.fqans)&&(userToVo.fqans != '')}">
					<c:set var="strings" value="${fn:split(userToVo.fqans,';')}" />
					<c:forEach var="fqan" items="${strings}">

						<input name="resultList" type="hidden" value="${fqan}" />

					</c:forEach>
				</c:if>


			</div>

			<aui:button-row>
			
				<aui:button type="submit" value="Salva Lista"/>

				<portlet:renderURL var="backURL">
					<portlet:param name="myaction" value="editUserInfoForm" />
					<portlet:param name="userId" value="${userId}" />
				</portlet:renderURL>
				<aui:button type="cancel" value="Back"
					onClick="location.href='${backURL}';" />

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>
