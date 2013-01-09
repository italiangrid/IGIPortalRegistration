<%@ include file="/WEB-INF/jsp/init.jsp"%>
<style type="text/css">
#summary {
	width: 20%;
	box-shadow: 10px 10px 5px #888;
	border: 1px;
	border-color: #ACDFA7;
	border-style: solid;
	background-color: #f4fdef;
	border-radius: 5px;
	padding: 10px;
	margin-right: 5px;
	float: left;
}

.head {
	padding-bottom: 7px;
}

.info td {
	margin-left: 2px;
	min-width: 70px;
}

.info thead {
	font-weight: bold;
	font-size: 13px;
	text-decoration: underline;
}

.info {
	width: 100%;
}

.key {
	font-weight: bold;
}

.value {
	text-align: left;
}
</style>

<div id="summary">
	<aui:fieldset label="Registration Summary">

		<br />
		<br />

		<table class="info">
			<thead>
				<tr>
					<td class="head">User Data</td>
					<td class="head"><c:if
							test="${registrationModel.userStatus == true }">
							<img src="<%=request.getContextPath()%>/images/NewCheck.png"
								width="16" height="16" style="float: right" />
						</c:if> <c:if test="${registrationModel.userStatus == false }">
							<img src="<%=request.getContextPath()%>/images/NewDelete.png"
								width="16" height="16" style="float: right" />
						</c:if></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="key">First Name:</td>
					<td class="value">${registrationModel.firstName }</td>
				</tr>
				<tr>
					<td class="key">Last Name:</td>
					<td class="value">${registrationModel.lastName }</td>
				</tr>
				<tr>
					<td class="key">e-Mail:</td>
					<td class="value">${registrationModel.email }</td>
				</tr>
				<tr>
					<td class="key">Institute:</td>
					<td class="value">${registrationModel.institute }</td>
				</tr>
			</tbody>
		</table>
		<br />
		<br />

		<table class="info">
			<thead>
				<tr>
					<td class="head">Certificate</td>
					<td class="head"><c:if
							test="${registrationModel.certificateStatus == true }">
							<img src="<%=request.getContextPath()%>/images/NewCheck.png"
								width="16" height="16" style="float: right" />
						</c:if> <c:if test="${registrationModel.certificateStatus == false }">
							<img src="<%=request.getContextPath()%>/images/NewDelete.png"
								width="16" height="16" style="float: right" />
						</c:if></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="key">Subject:</td>
					<td class="value">${registrationModel.subject }</td>
				</tr>
				<tr>
					<td class="key">Expiration:</td>
					<td class="value">${registrationModel.expiration }</td>
				</tr>
			</tbody>
		</table>
		<br />
		<br />

		<table class="info">
			<thead>
				<tr>
					<td class="head">VO</td>
					<td class="head"><c:if
							test="${registrationModel.voStatus == true }">
							<img src="<%=request.getContextPath()%>/images/NewCheck.png"
								width="16" height="16" style="float: right" />
						</c:if> <c:if test="${registrationModel.voStatus == false }">
							<img src="<%=request.getContextPath()%>/images/NewDelete.png"
								width="16" height="16" style="float: right" />
						</c:if></td>
				</tr>
			</thead>
			<tbody>

				<c:if test="${selectedVos!=null }">
					
					<c:forEach var="vo" items="${selectedVos }"> 
						<tr>
							<td class="key">VO:</td>
							<td class="value">${vo.vo }</td>
						</tr>

					</c:forEach>

				</c:if>

			</tbody>
		</table>

	</aui:fieldset>

</div>