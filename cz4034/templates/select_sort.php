<?php if ($view != "words" && $view != 'trend'): ?>
  <div id="sort" class="row float-right">
    <label for="select_sort"><?= t('Sort') ?></label>
    <select id="select_sort">

      <?php $sort_relevance = (is_null($sort)) ? 'selected' : ''; ?>
      <option <?= $sort_relevance ?>
        value="<?= buildurl($params, "sort", NULL, 's', 1) ?>"><?= t('') ?></option>

      <?php $sort_newest = (isset($sort) && $sort == 'Newest') ? 'selected' : ''; ?>
      <option <?= $sort_newest ?>
        value="<?= buildurl($params, "sort", 'created_on desc', 's', 1) ?>"><?= t('Newest') ?></option>

      <?php $sort_oldest = (isset($sort) && $sort == 'Oldest') ? 'selected' : ''; ?>
      <option <?= $sort_oldest ?>
        value="<?= buildurl($params, "sort", 'created_on asc', 's', 1) ?>"><?= t('Oldest') ?></option>

    </select>
    <script type="text/javascript">
      $('#select_sort').change(function () {
        var dest = $(this).val();
        waiting_on();
        window.location = dest;
      });
    </script>
  </div>
<?php endif; ?>
