<table class="standard-box">
	<thead class="title">
	<tr>
		<th>id</th>
		<th>data</th>
		<th>grantodawca</th>
		<th>odbiorca</th>
		<th>suma, $</th>
	</tr>
	</thead>
	<tbody class="content">
	<?php while ($row = mysqli_fetch_row($result)) {
        $html_row = join('</td><td>', $row);
		$html_row = preg_replace("/viš/", "<a href='https://t.me/s/veisa'>viš</a>", $html_row);
		echo "<tr><td>$html_row</td></tr>";
	} ?>
	</tbody>
</table>