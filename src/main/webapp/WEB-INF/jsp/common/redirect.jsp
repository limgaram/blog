<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
    const alertMsg = '${msg}'.trim();

    if (alertMsg) {
        alert(alertMsg);
    }

    const historyBack = '${historyBack}' == 'true';

    if (historyBack) {
        history.Back();
    }

    const replaceUrl = '${replaceUrl}'.trim();

    if (redirectUrl) {
        location.replace(replaceUrl);
    }
</script>