document.addEventListener('DOMContentLoaded', () => {
    const searchForm = document.getElementById('search-form');
    const resultsContainer = document.getElementById('results-container');
    const loading = document.getElementById('loading');
    const error = document.getElementById('error-message');

    searchForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const source = document.getElementById('source').value.trim().toUpperCase();
        const destination = document.getElementById('destination').value.trim().toUpperCase();

        if (!source || !destination) {
            alert("Please enter both source and destination station codes.");
            return;
        }

        resultsContainer.innerHTML = '';
        resultsContainer.classList.remove('active');
        error.style.display = 'none';
        loading.style.display = 'block';

        try {
            const res = await fetch(`http://localhost:8080/search/by-code?sourceCode=${source}&destinationCode=${destination}`);

            if (!res.ok) {
                throw new Error(`HTTP error: ${res.status}`);
            }

            const data = await res.json();
            loading.style.display = 'none';
            renderResults(data);
        } catch (err) {
            console.error("Error fetching train data:", err);
            loading.style.display = 'none';
            error.style.display = 'block';
        }
    });

    function renderResults(trains) {
        resultsContainer.innerHTML = '';
        resultsContainer.classList.add('active');

        if (!Array.isArray(trains) || trains.length === 0) {
            resultsContainer.innerHTML = `
                <div class="no-results">
                    <h3>No trains found</h3>
                    <p>Try different station codes or check your spelling.</p>
                </div>
            `;
            return;
        }

        trains.forEach((schedule, i) => {
            const card = document.createElement('div');
            card.className = 'result-card';
            card.innerHTML = `
                <div class="train-header">
                    <div class="train-name">${schedule.train?.trainName || 'N/A'}</div>
                    <div class="train-number">${schedule.train?.trainNumber || 'N/A'}</div>
                </div>
                <div class="journey-details">
                    <div class="station departure">
                        <div class="time">${schedule.departureTime || '--:--'}</div>
                        <div class="station-name">${schedule.source?.stationName || '---'}</div>
                        <div class="station-code">${schedule.source?.stationCode || '---'}</div>
                    </div>
                    <div class="connector"><div class="line"></div></div>
                    <div class="station arrival">
                        <div class="time">${schedule.arrivalTime || '--:--'}</div>
                        <div class="station-name">${schedule.destination?.stationName || '---'}</div>
                        <div class="station-code">${schedule.destination?.stationCode || '---'}</div>
                    </div>
                </div>
                <div class="book-button">
                    <button class="book-btn">Book Now</button>
                </div>
            `;
            resultsContainer.appendChild(card);

            setTimeout(() => card.classList.add('visible'), 100 * i);
        });

        document.querySelectorAll('.book-btn').forEach(btn =>
            btn.addEventListener('click', () => {
                alert('Booking feature coming soon!');
            })
        );
    }
});
