<?php
// Determine which tab is active for rendering.
$view_selectors = array(
  'list' => t('List'),
);


$tabs = [];
foreach ($view_selectors as $selector => $title) {
  $tab = [
    'class' => ['button', 'secondary'],
    'title' => $title,
    'onclick' => 'waiting_on();',
  ];

  switch ($selector) {
    case 'list':
      if ($view === 'preview') {
        // if switching back from preview mode to list, dont reset start to first result
        $pagestart = (floor(($start-1) / $limit_list) * $limit_list ) + 1;
        $tab['url'] = buildurl($params, 'view', NULL, 's', $pagestart);
      }
      else {
        // Switching from other view like images or table, so reset start to first result
        $tab['url'] = buildurl($params, 'view', NULL, 's', 1);
      }
      break;

    default:
      $tab['url'] = buildurl($params, 'view', $selector, 's', 1);
      break;
  }

  if ($view === $selector) {
    $tab['class'][] = 'active';
    $tab['url'] = '#';
  }

  $tabs[$selector] = $tab;
}

?>

<div id="select_view" class="row">

  <div class="button-group">

    <?php
         include 'templates/select_sort.php';
    ?>
  </div>

</div>
