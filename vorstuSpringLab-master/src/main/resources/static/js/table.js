document.addEventListener('DOMContentLoaded', function () {
  const search = document.getElementById('search');
  const table = document.getElementById('students-table');
  const tbody = table.querySelector('tbody');

  function filterRows(term){
    term = term.trim().toLowerCase();
    Array.from(tbody.rows).forEach(r => {
      const text = r.innerText.toLowerCase();
      r.style.display = text.includes(term) ? '' : 'none';
    });
  }

  search.addEventListener('input', (e) => filterRows(e.target.value));

  document.getElementById('refresh').addEventListener('click', () => {
    // Пример: можно раскомментировать и настроить fetch к backend
    // fetch('/api/student/1/0/20').then(r=>r.json()).then(data=>populate(data.content))
    // пока просто сбрасываем поиск
    search.value = '';
    filterRows('');
  });

  // Утилита для заполнения таблицы данными из API
  function populate(items){
    tbody.innerHTML = '';
    items.forEach((it, idx) => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td data-label="#">${idx+1}</td>
        <td data-label="ФИО">${escapeHtml(it.fullName|| (it.lastName+' '+it.firstName || ''))}</td>
        <td data-label="Группа">${escapeHtml(it.group?.groupName || '')}</td>
        <td data-label="Email">${escapeHtml(it.email || '')}</td>
        <td data-label="Телефон">${escapeHtml(it.phone || '')}</td>
      `;
      tbody.appendChild(tr);
    });
  }

  function escapeHtml(s){ if(!s) return ''; return String(s).replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;'); }
});
