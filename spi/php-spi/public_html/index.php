<?php
$page = strtok(str_replace("/~edvin/", "", $_SERVER["REQUEST_URI"]), "?");

$base_url = "http://$_SERVER[HTTP_HOST]/~edvin";
$main_page_url = "$base_url/home";

if (!file_exists("pages/$page/index.phtml")) {
	http_response_code(404);
	$page = "404";
}

ob_start();
include "pages/$page/index.phtml";
$page_content = ob_get_clean();

$nav_links = array(
	"home" => "O mnie",
	"base" => "Baza grantów",
	"editbase" => "Edycja bazy",
	"tobase" => "Dodaj grant",
	"searchbase" => "Szukaj granty",
	"form" => "Formularz",
);

include "layout/index.phtml";